package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.Form;

@Repository
public interface FormRepository extends JpaRepository<Form,Integer> {
    boolean existsByName(String name);
}
