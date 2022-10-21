package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;
import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {
    List<Topic> findByOrganId(Integer id);
}
