package sokhoahoccongnghe.phutho.gov.vn.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Organ;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;
import sokhoahoccongnghe.phutho.gov.vn.exception.NotFoundException;
import sokhoahoccongnghe.phutho.gov.vn.mapper.OrganMapper;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.OrganRepository;
import sokhoahoccongnghe.phutho.gov.vn.repository.TopicRepository;

import java.util.List;
import java.util.function.Function;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private OrganRepository organRepository;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private OrganMapper organMapper;

    @Override
    public List<TopicDto> getTopics(Integer organId) {
        if (!organRepository.existsById(organId))
            throw new NotFoundException(organId, "organ");
        List<Topic> topicListEntity = topicRepository.findByOrganId(organId);
        return topicMapper.listEntity2Dto(topicListEntity);
    }

    @Override
    public TopicDto getTopic(Integer id) {
        Topic topicEntity = this.getTopicEntity(id);
        return topicMapper.entity2Dto(topicEntity);
    }

    @Override
    public TopicDto createTopic(Integer organId, TopicDto topicRequest) {
        return organRepository.findById(organId)
                .map(organ -> {
                    topicRequest.setOrgan(organMapper.entity2Dto(organ));
                    Topic topicCreated = topicRepository.save(topicMapper.dto2Entity(topicRequest));
                    return topicMapper.entity2Dto(topicCreated);
                })
                .orElseThrow(() -> new NotFoundException(organId, "organ"));
    }

    @Override
    public void udpateTopic(Integer id, TopicDto topicRequest) {
        Topic topicFinded = this.getTopicEntity(id);
        topicFinded.setManager(topicRequest.getManager());
        topicFinded.setName(topicRequest.getName());
        topicFinded.setStartDate(topicRequest.getStartDate());
        topicFinded.setEndDate(topicRequest.getEndDate());
        topicFinded.setExpense(topicRequest.getExpense());
        topicRepository.save(topicFinded);
    }

    @Override
    public void deleteTopic(Integer id) {
        Topic topicFinded = this.getTopicEntity(id);
        topicRepository.delete(topicFinded);
    }

    public Topic getTopicEntity(Integer id) {
        //return entity if exist,else throw exception
        return topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "topic"));
    }
}
