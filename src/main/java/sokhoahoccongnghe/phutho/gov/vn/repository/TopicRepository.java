package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.*;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {
//    List<Topic> findByOrgan(Organ o);
    List<Topic> findByTopicField(TopicField f);
    List<Topic> findByTopicStatus(TopicStatus s);
//    List<Topic> findByOrganAndTopicStatus(Organ o,TopicStatus s);
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
    @Query(value =
            "select t from Topic t where (:topicName is null or t.name like %:topicName%) and t.manager.username" +
                    " = :username and (:field is null or t.topicField = :field) and (:status is null or t.topicStatus" +
                    " =:status)")
    Page<Topic> findByManagerWithFilter(@Param("username") String username,
                                        @Param("topicName") String topicName,
                                        @Param("status") TopicStatus status,
                                        @Param("field") TopicField field,
                                        Pageable p);
    @Query(value ="select t from Topic t where t.topicStatus <> :statusIgnore and (:topicName is null or t.name like " +
            "%:topicName%) and (:managerName is null or t.manager.name like %:managerName%) and (:statusFilter is " +
            "null or t.topicStatus = :statusFilter) and (:organ is null or t.manager.organ = :organ)")
    Page<Topic> findByAdminWithFilter(@Param("statusIgnore") TopicStatus statusIgnore,
                                      @Param("statusFilter") TopicStatus statusFilter,
                                      @Param("topicName") String topicName,
                                      @Param("managerName") String managerName,
                                      @Param("organ") Organ organ,
                                      Pageable p);

    @Query(value ="select t from Topic t where t.topicStatus = :status and (:organ is null or t.manager.organ = :organ)")
    Page<Topic> findByStatusWithFilter(@Param("status") TopicStatus status,
                                       @Param("organ") Organ organ,
                                      Pageable p);



//    @Query(value = "")
//    Page<Topic> findApprovedTopicWithFilter();
//    Topic findFirstByUid(String uid);
//    long countByTopicStatusAndOrgan(TopicStatus s, Organ o);
//    long countByTopicResultAndOrgan(TopicResult r, Organ o);
    long countByName(String name);
    boolean existsByName(String name);

    @Query(value = "select count(t) from Topic t where (:username is null or t.manager.username = :username) and t.topicStatus = :status")
    long countTopicByManagerAndStatus(@Param("username") String username,@Param("status") TopicStatus status);

    @Query(value = "select count(t) from Topic t where (:username is null or t.manager.username = :username) and t.topicResult = :result")
    long countTopicByManagerAndResult(@Param("username") String username,@Param("result") TopicResult result);

    @Modifying
//    @Query(value = "update de_tai set xoa = true WHERE ma=?",nativeQuery = true) // native sql
    @Query(value = "update Topic t set t.deleted = true where t.id = :id") // jpql
    void softDeleteTopicById(@Param("id") Integer id);
}
