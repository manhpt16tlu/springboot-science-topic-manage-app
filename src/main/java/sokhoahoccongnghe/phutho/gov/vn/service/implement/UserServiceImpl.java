package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sokhoahoccongnghe.phutho.gov.vn.dto.UserDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Organ;
import sokhoahoccongnghe.phutho.gov.vn.entity.Rank;
import sokhoahoccongnghe.phutho.gov.vn.entity.Role;
import sokhoahoccongnghe.phutho.gov.vn.entity.User;
import sokhoahoccongnghe.phutho.gov.vn.exception.NotFoundException;
import sokhoahoccongnghe.phutho.gov.vn.mapper.RankMapper;
import sokhoahoccongnghe.phutho.gov.vn.mapper.RoleMapper;
import sokhoahoccongnghe.phutho.gov.vn.mapper.UserMapper;
import sokhoahoccongnghe.phutho.gov.vn.model.RoleNameEnum;
import sokhoahoccongnghe.phutho.gov.vn.repository.UserRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.RoleService;
import sokhoahoccongnghe.phutho.gov.vn.service.UserService;
import sokhoahoccongnghe.phutho.gov.vn.util.GetEntityById;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RankMapper rankMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::entity2Dto)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public boolean checkExistByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public UserDto createUser(UserDto userRequest) {
        User createdUser = userRepository.save(userMapper.dto2Entity(userRequest));
        return userMapper.entity2Dto(createdUser);
    }

    @Override
    public Page<UserDto> getAll(int page, int size,String organ,String username) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");//tạo sau thì hiển thị trước
        Pageable paging = PageRequest.of(page, size, sort);
        Gson gson = new Gson();
        Organ organFilterEntity = gson.fromJson(organ,Organ.class);
        Role adminRole = roleService
                .getRoleByName(RoleNameEnum.ADMIN)
                .map(roleMapper::dto2Entity)
                .orElseThrow(()->new NotFoundException("role"));
        Page<User> userPageEntity = userRepository.getAll(adminRole,organFilterEntity,username,paging);
        return userPageEntity.map(userMapper::entity2Dto);
    }

    @Override
    public void disableUser(Integer userId) {
        User userEntity = GetEntityById.getEntity(userRepository,userId);
        //toggle
        userEntity.setDisabled(!userEntity.isDisabled());
        userRepository.save(userEntity);
    }

    @Override
    public void updateInformation(Integer userId,UserDto userRequest) {
          User userEntity = GetEntityById.getEntity(userRepository,userId);
          userEntity.setName(userRequest.getName());
          Rank rankRequestEntity = rankMapper.dto2Entity(userRequest.getRank());
          userEntity.setRank(rankRequestEntity);
          User newUser = userRepository.save(userEntity);
    }

    @Override
    public void changePassword(String newPass,String oldPass) {
        UserDetails currentUser = getPrincipal();
        String currentUsername = currentUser.getUsername();
        User userEntity = userRepository.findByUsername(currentUsername).orElseThrow();
        if(passwordEncoder.matches(oldPass,userEntity.getPassword())){
           userEntity.setPassword(passwordEncoder.encode(newPass));
           User newUser = userRepository.save(userEntity);
        } else throw new RuntimeException("wrong password");
    }

    @Override
    public UserDetails getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails)authentication.getPrincipal();
    }
}
