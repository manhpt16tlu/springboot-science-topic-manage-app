package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.RoleDto;
import sokhoahoccongnghe.phutho.gov.vn.mapper.RoleMapper;
import sokhoahoccongnghe.phutho.gov.vn.model.RoleNameEnum;
import sokhoahoccongnghe.phutho.gov.vn.repository.RoleRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.RoleService;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Optional<RoleDto> getRoleByName(RoleNameEnum name) {
       return roleRepository.findByName(name).map(roleMapper::entity2Dto);
    }
}
