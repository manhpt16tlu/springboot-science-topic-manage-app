package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.Organ;
import sokhoahoccongnghe.phutho.gov.vn.entity.Role;
import sokhoahoccongnghe.phutho.gov.vn.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    @Query(value ="select u from User u inner join u.roles r where" +
            " (:organ is null or u.organ = :organ) and (:username is null or u.username like %:username%) and r <> " +
            ":role")
    Page<User> getAll(@Param("role") Role role,@Param("organ") Organ organ,@Param("username") String username,
                      Pageable p);
}
