package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.FormFile;

@Repository
public interface FormFileRepository extends JpaRepository<FormFile,String> {
    FormFile findFirstByFormId(Integer formId);
}
