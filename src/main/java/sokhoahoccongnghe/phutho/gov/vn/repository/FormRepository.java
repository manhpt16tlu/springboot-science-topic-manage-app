package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.Form;
import sokhoahoccongnghe.phutho.gov.vn.entity.FormType;

@Repository
public interface FormRepository extends JpaRepository<Form,Integer> {
    boolean existsByName(String name);

    @Query(value ="select f from Form f where (:name is null or f.name like %:name%) and (:type is null or f.type " +
            "= :type)")
    Page<Form> getAll(@Param("name") String formName,@Param("type") FormType formType,
                      Pageable p);
}
