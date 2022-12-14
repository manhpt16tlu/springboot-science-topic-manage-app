package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicStatus;

@Repository
public interface TopicStatusRepository extends JpaRepository<TopicStatus,Integer> {
    TopicStatus findFirstByTitle(String title);
    TopicStatus findByTitle(String title);
}
