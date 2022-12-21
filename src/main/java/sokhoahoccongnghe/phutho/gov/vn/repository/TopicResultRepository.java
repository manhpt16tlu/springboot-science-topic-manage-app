package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicResult;

@Repository
public interface TopicResultRepository extends JpaRepository<TopicResult,Integer> {
    TopicResult findFirstByTitle(String title);
    TopicResult findByTitle(String title);
}
