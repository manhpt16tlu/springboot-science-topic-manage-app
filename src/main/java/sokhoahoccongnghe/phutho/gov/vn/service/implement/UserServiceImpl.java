package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sokhoahoccongnghe.phutho.gov.vn.dto.UserDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.User;
import sokhoahoccongnghe.phutho.gov.vn.mapper.UserMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.UserRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
    @Override
    public Optional<UserDto> getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::entity2Dto);
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
}
