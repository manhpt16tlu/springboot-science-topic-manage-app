package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.OrganDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.RankDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.RoleDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.UserDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Organ;
import sokhoahoccongnghe.phutho.gov.vn.entity.Rank;
import sokhoahoccongnghe.phutho.gov.vn.entity.Role;
import sokhoahoccongnghe.phutho.gov.vn.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto entity2Dto(User u);
    User dto2Entity(UserDto u);
    RoleDto roleEntity2Dto(Role r);
    OrganDto organEntity2Dto(Organ o);
    Role roleDto2Entity(RoleDto r);
    Organ organDto2Entity(OrganDto o);
    RankDto rankEntity2Dto(Rank r);
    Rank rankDto2Entity(RankDto r);
}
