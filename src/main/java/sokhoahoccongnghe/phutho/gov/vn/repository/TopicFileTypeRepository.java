package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicFileType;

@Repository
public interface TopicFileTypeRepository extends JpaRepository<TopicFileType,Integer> {
    TopicFileType findFirstByTitleContainingIgnoreCase(String title);
}
