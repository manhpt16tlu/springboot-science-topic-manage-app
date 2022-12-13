package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.Role;
import sokhoahoccongnghe.phutho.gov.vn.model.RoleNameEnum;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(RoleNameEnum name);
}
