package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.data.domain.Page;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;

import java.util.List;

public interface TopicService {
//   List<TopicDto> getTopicsByOrgan(Integer organId);
   TopicDto getTopic(Integer id);
   TopicDto createTopic(Integer organId,Integer fieldId,Integer statusId,Integer resultId,TopicDto topicRequest);
   TopicDto employeeCreateTopic(TopicDto topicRequest);
   void updateTopic(Integer id,TopicDto topicRequest);
   void deleteTopic(Integer id);
   void approveTopic(Integer topicId);
   List<TopicDto> getTopicsByField(Integer fieldId);
   List<TopicDto> getTopicsNoPaging();
   Page<TopicDto> getTopics(int page,int size);
   Page<TopicDto> getApprovedTopics(int page,int size);
   Page<TopicDto> getFilteredApprovedTopics(int page,int size,String searchName,List<String> organFilter,
                                            String searchManganer,String statusFilter);
   List<TopicDto> getNonApprovedTopicsByOrgan(Integer organId);
   List<TopicDto> getTopicsByStatus(Integer statusId);
   List<TopicDto> getTopicsByResult(Integer resultId);
   Page<TopicDto> getTopicByUsernameWithFilter(int page,int size,String username,String topicName,String status,
                                             String field);
   Page<TopicDto> getTopicByAdminWithFilter(int page, int size, String topicName, String organ, String managerName,
                                            String status);
   Page<TopicDto> getNotApproveTopicListByAdminWithFilter(int page, int size,String organ);
//   TopicDto getTopicByUID(String uid);
//   long countTopicByStatusId(Integer organId,Integer statusId);
//   long countTopicByStatusName(Integer organId,String statusName);
//   long countTopicByResult(Integer organId,Integer resultId);
   long countTopicByName(String name);
   long countTopicByManagerAndStatus(String statusName,String username);
   long countTopicByManagerAndResult(String resultName,String username);
   long countTopicByStatus(String statusName);
   long countTopicByResult(String resultName);
   boolean existByName(String name);
}
