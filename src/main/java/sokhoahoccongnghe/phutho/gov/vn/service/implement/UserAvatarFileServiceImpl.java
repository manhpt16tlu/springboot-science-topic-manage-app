package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sokhoahoccongnghe.phutho.gov.vn.dto.UserAvatarFileDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.User;
import sokhoahoccongnghe.phutho.gov.vn.entity.UserAvatarFile;
import sokhoahoccongnghe.phutho.gov.vn.exception.FileDeleteException;
import sokhoahoccongnghe.phutho.gov.vn.mapper.UserAvatarFileMapper;
import sokhoahoccongnghe.phutho.gov.vn.mapper.UserMapper;
import sokhoahoccongnghe.phutho.gov.vn.model.FileTypeEnum;
import sokhoahoccongnghe.phutho.gov.vn.repository.UserAvatarFileRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.FileStorageService;
import sokhoahoccongnghe.phutho.gov.vn.service.UserAvatarFileService;
import sokhoahoccongnghe.phutho.gov.vn.service.UserService;

import java.io.IOException;
import java.util.List;

@Service
public class UserAvatarFileServiceImpl implements UserAvatarFileService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAvatarFileRepository avatarFileRepository;

    @Autowired
    private UserAvatarFileMapper avatarFileMapper;


    @Override
    public List<UserAvatarFileDto> getAll() {
        return null;
    }

    @Override
    public UserAvatarFileDto getAvatarOfCurrentUser() {
        UserDetails currentUser = userService.getPrincipal();
        String username = currentUser.getUsername();
        return getAvatarOfUser(username);
    }

    @Override
    public UserAvatarFileDto getAvatarOfUser(String username) {
        User userEntity = userMapper.dto2Entity(userService.getUserByUsername(username));
        UserAvatarFile avatarFileEntity = avatarFileRepository.getByUser(userEntity);
        return avatarFileMapper.entity2Dto(avatarFileEntity);
    }

    @Override
    public UserAvatarFileDto createAvatarFile(UserAvatarFileDto avatarFileDto) {
        UserAvatarFile avatarFileEntity = avatarFileMapper.dto2Entity(avatarFileDto);
        UserAvatarFile avatarFileCreated = avatarFileRepository.save(avatarFileEntity);
        return avatarFileMapper.entity2Dto(avatarFileCreated);
    }

    @Override
    @Transactional
    public UserAvatarFileDto removeAvatar(String username) {
        User userEntity = userMapper.dto2Entity(userService.getUserByUsername(username));
        List<UserAvatarFile> deletedAvatarFiles =avatarFileRepository.deleteByUser(userEntity);
        return avatarFileMapper.entity2Dto(deletedAvatarFiles.get(0)); // list có 1 phần tử
    }

    @Override
    public boolean checkExistByFileCode(String fileCode) {
        return avatarFileRepository.existsById(fileCode);
    }

    @Override
    public boolean checkExistByUser(String username) {
        User userEntity = userMapper.dto2Entity(userService.getUserByUsername(username));
        return avatarFileRepository.existsByUser(userEntity);
    }

}
