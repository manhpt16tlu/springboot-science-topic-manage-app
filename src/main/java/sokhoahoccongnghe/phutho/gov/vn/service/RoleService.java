package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.RoleDto;
import sokhoahoccongnghe.phutho.gov.vn.model.RoleNameEnum;

import java.util.Optional;

public interface RoleService {
    Optional<RoleDto> getRoleByName(RoleNameEnum name);

}
