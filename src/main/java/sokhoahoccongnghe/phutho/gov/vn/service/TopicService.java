package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.data.domain.Page;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;

import java.util.List;

public interface TopicService {
   List<TopicDto> getTopicsByOrgan(Integer organId);
   TopicDto getTopic(Integer id);
   TopicDto createTopic(Integer organId,Integer fieldId,Integer statusId,Integer resultId,TopicDto topicRequest);
   void udpateTopic(Integer id,TopicDto topicRequest);
   void deleteTopic(Integer id);
   List<TopicDto> getTopicsByField(Integer fieldId);
   List<TopicDto> getTopicsNoPaging();
   Page<TopicDto> getTopics(int page,int size);
   Page<TopicDto> getApprovedTopics(int page,int size);
   List<TopicDto> getNonApprovedTopics();
   List<TopicDto> getTopicsByStatus(Integer statusId);
   List<TopicDto> getTopicsByResult(Integer resultId);
   TopicDto getTopicByUID(String uid);
   long countTopicByStatus(Integer organId,Integer statusId);
   long countTopicByResult(Integer organId,Integer resultId);
   long countTopicByName(String name);
}
