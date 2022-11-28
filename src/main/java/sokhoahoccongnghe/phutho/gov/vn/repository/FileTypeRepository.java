package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.FileType;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicResult;

@Repository
public interface FileTypeRepository extends JpaRepository<FileType,Integer> {
    FileType findFirstByName(String name);
}
