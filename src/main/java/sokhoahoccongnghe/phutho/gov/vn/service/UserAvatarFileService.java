package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.UserAvatarFileDto;

import java.util.List;

public interface UserAvatarFileService {
    List<UserAvatarFileDto> getAll();
    UserAvatarFileDto getAvatarOfCurrentUser();
    UserAvatarFileDto getAvatarOfUser(String username);
    UserAvatarFileDto createAvatarFile(UserAvatarFileDto avatarFileDto);
    UserAvatarFileDto removeAvatar(String username);
    boolean checkExistByFileCode(String fileCode);
    boolean checkExistByUser(String username);
}
