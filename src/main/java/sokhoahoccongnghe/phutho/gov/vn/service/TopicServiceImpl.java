package sokhoahoccongnghe.phutho.gov.vn.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.TopicRepository;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepo;
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<TopicDto> getAll() {
        List<Topic> topics = topicRepo.findAll();
        List<TopicDto> topicDtos = topicMapper.mapListTopic2TopicDto(topics);
        return topicDtos;
    }
}
