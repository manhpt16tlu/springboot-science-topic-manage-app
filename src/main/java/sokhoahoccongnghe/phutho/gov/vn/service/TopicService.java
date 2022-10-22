package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;

import java.util.List;

public interface TopicService {
   List<TopicDto> getTopicsByOrgan(Integer organId);
   TopicDto getTopic(Integer id);
   TopicDto createTopic(Integer organId,Integer fieldId,TopicDto topicRequest);
   void udpateTopic(Integer id,TopicDto topicRequest);
   void deleteTopic(Integer id);
   List<TopicDto> getTopicsByField(Integer fieldId);
}
