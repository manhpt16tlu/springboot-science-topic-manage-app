package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.RoleDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto entity2Dto(Role r);
    Role dto2Entity(RoleDto r);
}
