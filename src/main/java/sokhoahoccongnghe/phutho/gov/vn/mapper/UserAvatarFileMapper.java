package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.UserAvatarFileDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.UserDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.User;
import sokhoahoccongnghe.phutho.gov.vn.entity.UserAvatarFile;

@Mapper(componentModel = "spring")
public interface UserAvatarFileMapper {
    UserAvatarFileDto entity2Dto(UserAvatarFile u);
    UserAvatarFile dto2Entity(UserAvatarFileDto u);
    User userDto2Entity(UserDto u);
    UserDto userEntity2Dto(User u);
}
