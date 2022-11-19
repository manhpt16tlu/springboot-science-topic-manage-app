package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.*;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {
    List<Topic> findByOrgan(Organ o);
    List<Topic> findByTopicField(TopicField f);
    List<Topic> findByTopicStatus(TopicStatus s);
    List<Topic> findByTopicResult(TopicResult r);
    List<Topic> findByTopicStatusNot(TopicStatus s);
    Topic findFirstByUid(String uid);
    long countByTopicStatusAndOrgan(TopicStatus s,Organ o);
    long countByTopicResultAndOrgan(TopicResult r,Organ o);
    long countByName(String name);
}
