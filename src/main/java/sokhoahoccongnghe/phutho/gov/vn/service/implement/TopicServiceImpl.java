package sokhoahoccongnghe.phutho.gov.vn.service.implement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFieldDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicResultDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicStatusDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.*;
import sokhoahoccongnghe.phutho.gov.vn.exception.NotFoundException;
import sokhoahoccongnghe.phutho.gov.vn.exception.NullPropertyException;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.*;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicService;
import sokhoahoccongnghe.phutho.gov.vn.util.GetEntityById;

import java.util.Date;
import java.util.List;

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
        Topic topicEntity = GetEntityById.getEntity(topicRepository,id);
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

                    topicRequestEntity.setCreateDate(new Date());

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
    public TopicDto getTopicByUID(String uid) {
        Topic topicEntity = topicRepository.findFirstByUid(uid);
        return topicMapper.entity2Dto(topicEntity);
    }

    @Override
    public long countTopicByStatusId(Integer organId, Integer statusId) {
        Organ organEntity = GetEntityById.getEntity(organRepository,organId);
        TopicStatus statusEntity =  GetEntityById.getEntity(statusRepository,statusId);
        return topicRepository.countByTopicStatusAndOrgan(statusEntity,organEntity);
    }

    @Override
    public long countTopicByStatusName(Integer organId, String statusName) {
        Organ organEntity = GetEntityById.getEntity(organRepository,organId);
        TopicStatus statusEntity = statusRepository.findFirstByTitle(statusName); //name of status is 'title' in db
        return topicRepository.countByTopicStatusAndOrgan(statusEntity,organEntity);
    }

    @Override
    public long countTopicByResult(Integer organId, Integer resultId) {
        Organ organEntity = GetEntityById.getEntity(organRepository, organId);
        TopicResult resultEntity = GetEntityById.getEntity(resultRepository, resultId);
        return topicRepository.countByTopicResultAndOrgan(resultEntity, organEntity);
    }

    @Override
    public long countTopicByName(String name) {
        return topicRepository.countByName(name);
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
    public List<TopicDto> getTopicsNoPaging() {
        List<Topic> topicListEntity = topicRepository.findAll();
        return topicMapper.listEntity2Dto(topicListEntity);
    }

    @Override
    public Page<TopicDto> getTopics(int page,int size) {
        Pageable paging = PageRequest.of(page,size);
        Page<Topic> topicPageEntity = topicRepository.findAll(paging);
        return topicPageEntity.map(topicMapper::entity2Dto);
    }

    @Override
    public Page<TopicDto> getApprovedTopics(int page,int size) {
        Pageable paging = PageRequest.of(page,size);
        TopicStatus statusEntity = statusRepository.findFirstByTitle("Chưa duyệt");
        Page<Topic> topicPageEntity = topicRepository.findByTopicStatusNot(statusEntity,paging);
        return topicPageEntity.map(topicMapper::entity2Dto);
    }

    @Override
    public Page<TopicDto> getFilteredApprovedTopics(int page, int size, String searchName, List<String> organFilter,
                                                    String searchManganer,String statusFilter) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createDate");//tạo sau thì hiển thị trước
        Pageable paging = PageRequest.of(page, size,sort);  //nếu cần cả paging thì pass sort vào pageable

        TopicStatus statusEntity = statusRepository.findFirstByTitle("Chưa duyệt");
        Page<Topic> topicPageEntity;
        if(searchName.equals("") && organFilter.size() == 0 && searchManganer.equals("") && statusFilter.equals("")) {
            topicPageEntity = topicRepository.findByTopicStatusNot(statusEntity, paging);
        }
        else {
            if(organFilter.size() == 0)
                topicPageEntity =
                        topicRepository.findByTopicStatusNotAndTopicStatus_TitleContainingAndNameContainingAndOrgan_NameContainingAndManagerContaining(statusEntity,statusFilter, searchName, "", searchManganer, paging);
           else topicPageEntity =
                   topicRepository.findByTopicStatusNotAndTopicStatus_TitleContainingAndNameContainingAndOrgan_NameInAndManagerContaining(statusEntity,statusFilter, searchName, organFilter, searchManganer, paging);
        }
            return topicPageEntity.map(topicMapper::entity2Dto);
    }

    @Override
    public List<TopicDto> getNonApprovedTopicsByOrgan(Integer organId) {
        Organ organEntity = GetEntityById.getEntity(organRepository,organId);
        TopicStatus statusEntity = statusRepository.findFirstByTitle("Chưa duyệt"); // tạm thời fix cứng
        List<Topic> topicListEntity = topicRepository.findByOrganAndTopicStatus(organEntity,statusEntity);
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

    @Override
    public void approveTopic(Integer topicId, TopicDto topicRequest) {
        if(topicRequest.getTopicStatus() != null && topicRequest.getTopicStatus().getTitle() != null) {
            TopicStatus statusEntity = statusRepository.findFirstByTitle(topicRequest.getTopicStatus().getTitle());
            System.out.println(statusEntity);
            if(statusEntity != null) {
                Topic topicEntity = GetEntityById.getEntity(topicRepository, topicId);
                topicEntity.setTopicStatus(statusEntity);
                topicRepository.save(topicEntity);
            }
            else throw new NotFoundException("status");
        }
         else throw new NullPropertyException();
    }

    //deprecated
    public Topic getTopicEntity(Integer id) {
        //return entity if exist,else throw exception
        return topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "topic"));
    }
}
