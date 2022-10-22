package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.Organ;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicField;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {
    List<Topic> findByOrgan(Organ o);
    List<Topic> findByTopicField(TopicField f);
}
