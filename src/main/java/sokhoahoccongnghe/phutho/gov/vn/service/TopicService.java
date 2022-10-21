package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;

import java.util.List;

public interface TopicService {
   List<TopicDto> getTopics(Integer organId);
   TopicDto getTopic(Integer id);
   TopicDto createTopic(Integer organId,TopicDto topicRequest);
   void udpateTopic(Integer id,TopicDto topicRequest);
   void deleteTopic(Integer id);
}
