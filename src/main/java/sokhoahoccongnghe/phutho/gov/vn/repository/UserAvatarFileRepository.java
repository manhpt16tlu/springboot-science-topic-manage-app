package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.User;
import sokhoahoccongnghe.phutho.gov.vn.entity.UserAvatarFile;

import java.util.List;

@Repository
public interface UserAvatarFileRepository extends JpaRepository<UserAvatarFile,String> {

    @Query(value = "select a from UserAvatarFile a where a.user = :user")
    UserAvatarFile getByUser(@Param("user") User user);

    boolean existsByUser(User user);
    List<UserAvatarFile> deleteByUser(User user); //delete query return list record
}
