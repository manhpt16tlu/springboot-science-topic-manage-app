package sokhoahoccongnghe.phutho.gov.vn.service.implement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFieldDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicResultDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicStatusDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.*;
import sokhoahoccongnghe.phutho.gov.vn.exception.NotFoundException;
import sokhoahoccongnghe.phutho.gov.vn.exception.NullPropertyException;
import sokhoahoccongnghe.phutho.gov.vn.mapper.OrganMapper;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicFieldMapper;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicMapper;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicStatusMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.*;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicService;
import sokhoahoccongnghe.phutho.gov.vn.util.GetEntityById;

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
    private TopicResultRepository resultRepository;

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
    public TopicDto createTopic(Integer organId, Integer fieldId, Integer statusId,Integer resultId,
                                TopicDto topicRequest) {
        Topic topicRequestEntity = topicMapper.dto2Entity(topicRequest);
        return organRepository.findById(organId)
                .map(organ -> {
                    topicRequestEntity.setOrgan(organ);
                    TopicField fieldEntity = fieldRepository.findById(fieldId).orElseThrow(() -> new NotFoundException(fieldId, "topic field"));
                    topicRequestEntity.setTopicField(fieldEntity);
                    TopicStatus statusEntity =
                            statusRepository.findById(statusId).orElseThrow(() -> new NotFoundException(statusId, "topic status"));
                    topicRequestEntity.setTopicStatus(statusEntity);

                    TopicResult resultEntity = GetEntityById.getEntity(resultRepository,resultId);
                    topicRequestEntity.setTopicResult(resultEntity);

                    Topic topicCreated = topicRepository.save(topicRequestEntity);
                    return topicMapper.entity2Dto(topicCreated);
                })
                .orElseThrow(() -> new NotFoundException(organId, "organ"));
    }

    @Override
    public List<TopicDto> getTopicsByResult(Integer resultId) {
        TopicResult resultEntity = GetEntityById.getEntity(resultRepository,resultId);
        List<Topic> topicListEntity = topicRepository.findByTopicResult(resultEntity);
        return topicMapper.listEntity2Dto(topicListEntity);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public void udpateTopic(Integer id, TopicDto topicRequest) {
        Topic topicFinded = GetEntityById.getEntity(topicRepository, id);

        topicFinded.setManager(topicRequest.getManager());
        topicFinded.setName(topicRequest.getName());
        topicFinded.setStartDate(topicRequest.getStartDate());
        topicFinded.setEndDate(topicRequest.getEndDate());
        topicFinded.setExpense(topicRequest.getExpense());

        TopicFieldDto fieldOfTopicReq = topicRequest.getTopicField();
        TopicStatusDto statusOfTopicReq = topicRequest.getTopicStatus();
        TopicResultDto resultOfTopicReq = topicRequest.getTopicResult();

        if (fieldOfTopicReq != null && fieldOfTopicReq.getId() != null) {
            Integer fieldIdOfTopicRequest = fieldOfTopicReq.getId();
            TopicField fieldEntity = GetEntityById.getEntity(fieldRepository, fieldIdOfTopicRequest);
            topicFinded.setTopicField(fieldEntity);
        } else throw new NullPropertyException();

        if (statusOfTopicReq != null && statusOfTopicReq.getId() != null) {
            Integer statusIdOfTopicReq = statusOfTopicReq.getId();
            TopicStatus statusEntity = GetEntityById.getEntity(statusRepository, statusIdOfTopicReq);
            topicFinded.setTopicStatus(statusEntity);
        } else throw new NullPropertyException();

        if(resultOfTopicReq != null && resultOfTopicReq.getId() != null){
            Integer resultIdOfTopicReq = resultOfTopicReq.getId();
            TopicResult resultEntity = GetEntityById.getEntity(resultRepository,resultIdOfTopicReq);
            topicFinded.setTopicResult(resultEntity);
        }
        else throw new NullPropertyException();

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
                .orElseThrow(() -> new NotFoundException(statusId, "topic status"));
        List<Topic> topicListEntity = topicRepository.findByTopicStatus(statusEntity);
        return topicMapper.listEntity2Dto(topicListEntity);
    }

    @Override
    public void deleteTopic(Integer id) {
        Topic topicFinded = GetEntityById.getEntity(topicRepository, id);
        topicRepository.delete(topicFinded);
    }

    //deprecated
    public Topic getTopicEntity(Integer id) {
        //return entity if exist,else throw exception
        return topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "topic"));
    }
}
