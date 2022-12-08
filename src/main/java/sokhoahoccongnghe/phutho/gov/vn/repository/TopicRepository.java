package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.*;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {
    List<Topic> findByOrgan(Organ o);
    List<Topic> findByTopicField(TopicField f);
    List<Topic> findByTopicStatus(TopicStatus s);
    List<Topic> findByOrganAndTopicStatus(Organ o,TopicStatus s);
    List<Topic> findByTopicResult(TopicResult r);
    Page<Topic> findByTopicStatusNot(TopicStatus ts, Pageable p);
    Page<Topic> findByTopicStatusNotAndTopicStatus_TitleContainingAndNameContainingAndOrgan_NameInAndManagerContaining(TopicStatus s,
                                                                                                 String sf,
                                                                                                 String name,
                                                                                                 List<String> organ,
                                                                                                 String manager,
                                                                                                 Pageable p);
    Page<Topic> findByTopicStatusNotAndTopicStatus_TitleContainingAndNameContainingAndOrgan_NameContainingAndManagerContaining(TopicStatus s, String sf,
                                                                                         String name,
                                                                                         String organ,
                                                                                         String manager,
                                                                                         Pageable p);
//    @Query(value = "")
//    Page<Topic> findApprovedTopicWithFilter();
//    Topic findFirstByUid(String uid);
    long countByTopicStatusAndOrgan(TopicStatus s, Organ o);
    long countByTopicResultAndOrgan(TopicResult r, Organ o);
    long countByName(String name);
//    boolean existsByUid(String uid);

}
