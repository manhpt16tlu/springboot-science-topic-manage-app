package sokhoahoccongnghe.phutho.gov.vn.service.implement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFieldDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Organ;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicField;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicStatus;
import sokhoahoccongnghe.phutho.gov.vn.exception.NotFoundException;
import sokhoahoccongnghe.phutho.gov.vn.exception.NullPropertyException;
import sokhoahoccongnghe.phutho.gov.vn.mapper.OrganMapper;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicFieldMapper;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicMapper;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicStatusMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.OrganRepository;
import sokhoahoccongnghe.phutho.gov.vn.repository.TopicFieldRepository;
import sokhoahoccongnghe.phutho.gov.vn.repository.TopicRepository;
import sokhoahoccongnghe.phutho.gov.vn.repository.TopicStatusRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicService;

import java.util.List;
import java.util.Objects;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private OrganRepository organRepository;

    @Autowired
    private TopicFieldRepository fieldRepository;

    @Autowired
    private TopicStatusRepository statusRepository;

    @Autowired
    private TopicMapper topicMapper;

//    @Autowired
//    private OrganMapper organMapper;
//
//    @Autowired
//    private TopicFieldMapper fieldMapper;
//
//    @Autowired
//    private TopicStatusMapper statusMapper;


    @Override
    public List<TopicDto> getTopicsByOrgan(Integer organId) {
        Organ organEntity = organRepository.findById(organId)
                .orElseThrow(() -> new NotFoundException(organId, "organ"));
        List<Topic> topicListEntity = topicRepository.findByOrgan(organEntity);
        return topicMapper.listEntity2Dto(topicListEntity);
    }

    @Override
    public TopicDto getTopic(Integer id) {
        Topic topicEntity = this.getTopicEntity(id);
        return topicMapper.entity2Dto(topicEntity);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public TopicDto createTopic(Integer organId, Integer fieldId,Integer statusId, TopicDto topicRequest) {
        Topic topicRequestEntity = topicMapper.dto2Entity(topicRequest);
        return organRepository.findById(organId)
                .map(organ -> {
                    topicRequestEntity.setOrgan(organ);
                    TopicField fieldEntity = fieldRepository.findById(fieldId).orElseThrow(() -> new NotFoundException(fieldId, "topic field"));
                    topicRequestEntity.setTopicField(fieldEntity);
                    TopicStatus statusEntity =
                            statusRepository.findById(statusId).orElseThrow(() -> new NotFoundException(statusId, "topic status"));
                    topicRequestEntity.setTopicStatus(statusEntity);
                    Topic topicCreated = topicRepository.save(topicRequestEntity);
                    return topicMapper.entity2Dto(topicCreated);
                })
                .orElseThrow(() -> new NotFoundException(organId, "organ"));
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public void udpateTopic(Integer id, TopicDto topicRequest) {
        Topic topicFinded = this.getTopicEntity(id);
        topicFinded.setManager(topicRequest.getManager());
        topicFinded.setName(topicRequest.getName());
        topicFinded.setStartDate(topicRequest.getStartDate());
        topicFinded.setEndDate(topicRequest.getEndDate());
        topicFinded.setExpense(topicRequest.getExpense());
        TopicFieldDto fieldOfTopicRequest = topicRequest.getTopicField();
        if (fieldOfTopicRequest != null && fieldOfTopicRequest.getId() != null) {
            Integer fieldIdOfTopicRequest = fieldOfTopicRequest.getId();
            TopicField fieldEntity = fieldRepository
                    .findById(fieldIdOfTopicRequest)
                    .orElseThrow(() -> new NotFoundException(fieldIdOfTopicRequest, "topic field"));
            topicFinded.setTopicField(fieldEntity);
        } else throw new NullPropertyException();
        topicRepository.save(topicFinded);
    }

    @Override
    public List<TopicDto> getTopicsByField(Integer fieldId) {
        TopicField fieldEntity = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new NotFoundException(fieldId, "topic field"));
        List<Topic> topicListEntity = topicRepository.findByTopicField(fieldEntity);
        return topicMapper.listEntity2Dto(topicListEntity);
    }

    @Override
    public List<TopicDto> getTopics() {
        List<Topic> topicListEntity = topicRepository.findAll();
        return topicMapper.listEntity2Dto(topicListEntity);
    }

    @Override
    public List<TopicDto> getTopicsByStatus(Integer statusId) {
        TopicStatus statusEntity = statusRepository.findById(statusId)
                .orElseThrow(()->new NotFoundException(statusId, "topic status"));
        List<Topic> topicListEntity = topicRepository.findByTopicStatus(statusEntity);
        return topicMapper.listEntity2Dto(topicListEntity);
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
