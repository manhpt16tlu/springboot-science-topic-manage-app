package sokhoahoccongnghe.phutho.gov.vn.service.implement;


import com.google.gson.Gson;
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
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicResultMapper;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicStatusMapper;
import sokhoahoccongnghe.phutho.gov.vn.model.TopicResultEnum;
import sokhoahoccongnghe.phutho.gov.vn.model.TopicStatusEnum;
import sokhoahoccongnghe.phutho.gov.vn.repository.*;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicResultService;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicService;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicStatusService;
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

    @Autowired
    private TopicStatusMapper statusMapper;

    @Autowired
    private TopicResultMapper resultMapper;

    @Autowired
    private TopicResultService topicResultService;
    @Autowired
    private TopicStatusService topicStatusService;

//    @Override
//    public List<TopicDto> getTopicsByOrgan(Integer organId) {
//        Organ organEntity = organRepository.findById(organId)
//                .orElseThrow(() -> new NotFoundException(organId, "organ"));
//        List<Topic> topicListEntity = topicRepository.findByOrgan(organEntity);
//        return topicMapper.listEntity2Dto(topicListEntity);
//    }

    @Override
    public TopicDto getTopic(Integer id) {
        Topic topicEntity = GetEntityById.getEntity(topicRepository, id);
        return topicMapper.entity2Dto(topicEntity);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public TopicDto createTopic(Integer organId, Integer fieldId, Integer statusId, Integer resultId,
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

                    TopicResult resultEntity;
                    if (resultId != null) {
                        resultEntity = GetEntityById.getEntity(resultRepository, resultId);
                    } else
                        resultEntity = resultRepository.findFirstByTitle("Kh??ng x??c ?????nh");

                    topicRequestEntity.setTopicResult(resultEntity);

                    topicRequestEntity.setCreateDate(new Date());

                    Topic topicCreated = topicRepository.save(topicRequestEntity);
                    return topicMapper.entity2Dto(topicCreated);
                })
                .orElseThrow(() -> new NotFoundException(organId, "organ"));
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public TopicDto employeeCreateTopic(TopicDto topicRequest) {
        TopicResultDto topicResultDto = topicResultService.getResultByName(TopicResultEnum.KHONG_XAC_DINH.getValue());
        TopicStatusDto topicStatusDto = topicStatusService.getByName(TopicStatusEnum.CHUA_DUYET.getValue());
        topicRequest.setTopicStatus(topicStatusDto);
        topicRequest.setTopicResult(topicResultDto);
        topicRequest.setCreateDate(new Date());
        Topic topicRequestEntity = topicMapper.dto2Entity(topicRequest);
        Topic topicSaved = topicRepository.save(topicRequestEntity);
        return topicMapper.entity2Dto(topicSaved);
    }

    @Override
    public List<TopicDto> getTopicsByResult(Integer resultId) {
        TopicResult resultEntity = GetEntityById.getEntity(resultRepository, resultId);
        List<Topic> topicListEntity = topicRepository.findByTopicResult(resultEntity);
        return topicMapper.listEntity2Dto(topicListEntity);
    }

    @Override
    public Page<TopicDto> getTopicByUsernameWithFilter(int page, int size, String username,String topicName,
                                                      String status,
                                                     String field) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");//t???o sau th?? hi???n th??? tr?????c
        Pageable paging = PageRequest.of(page, size, sort);

        Gson gson = new Gson();
        TopicStatus statusFilterEntity = gson.fromJson(status,TopicStatus.class);
        TopicField fieldFilterEntity = gson.fromJson(field,TopicField.class);



        Page<Topic> topicPageEntity = topicRepository.findByManagerWithFilter(username,topicName,statusFilterEntity,
                fieldFilterEntity,paging);
        return topicPageEntity.map(topicMapper::entity2Dto);
    }

    @Override
    public Page<TopicDto> getTopicByAdminWithFilter(int page, int size,String topicName, String organ, String managerName,
                                                    String status) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");//t???o sau th?? hi???n th??? tr?????c
        Pageable paging = PageRequest.of(page, size, sort);

        Gson gson = new Gson();

        TopicStatus statusFilterEntity = gson.fromJson(status,TopicStatus.class);
        Organ organFilterEntity = gson.fromJson(organ,Organ.class);
        TopicStatus notApprovedStatus =
                statusMapper.dto2Entity(topicStatusService.getByName(TopicStatusEnum.CHUA_DUYET.getValue()));

        Page<Topic> topicPageEntity = topicRepository.findByAdminWithFilter(
                notApprovedStatus,
                statusFilterEntity,
                topicName,
                managerName,
                organFilterEntity,
                paging);
        return topicPageEntity.map(topicMapper::entity2Dto);
    }

    @Override
    public Page<TopicDto> getNotApproveTopicListByAdminWithFilter(int page, int size,String organ) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");//t???o sau th?? hi???n th??? tr?????c
        Pageable paging = PageRequest.of(page, size, sort);
        TopicStatus notApprovedStatus =
                statusMapper.dto2Entity(topicStatusService.getByName(TopicStatusEnum.CHUA_DUYET.getValue()));

        Gson gson = new Gson();
        Organ organFilterEntity = gson.fromJson(organ,Organ.class);

        Page<Topic> topicPageEntity = topicRepository.findByStatusWithFilter(
                notApprovedStatus,
                organFilterEntity,
                paging);
        return topicPageEntity.map(topicMapper::entity2Dto);
    }

//    @Override
//    public TopicDto getTopicByUID(String uid) {
//        Topic topicEntity = topicRepository.findFirstByUid(uid);
//        return topicMapper.entity2Dto(topicEntity);
//    }

//    @Override
//    public long countTopicByStatusId(Integer organId, Integer statusId) {
//        Organ organEntity = GetEntityById.getEntity(organRepository,organId);
//        TopicStatus statusEntity =  GetEntityById.getEntity(statusRepository,statusId);
//        return topicRepository.countByTopicStatusAndOrgan(statusEntity,organEntity);
//    }

//    @Override
//    public long countTopicByStatusName(Integer organId, String statusName) {
//        Organ organEntity = GetEntityById.getEntity(organRepository,organId);
//        TopicStatus statusEntity = statusRepository.findFirstByTitle(statusName); //name of status is 'title' in db
//        return topicRepository.countByTopicStatusAndOrgan(statusEntity,organEntity);
//    }

//    @Override
//    public long countTopicByResult(Integer organId, Integer resultId) {
//        Organ organEntity = GetEntityById.getEntity(organRepository, organId);
//        TopicResult resultEntity = GetEntityById.getEntity(resultRepository, resultId);
//        return topicRepository.countByTopicResultAndOrgan(resultEntity, organEntity);
//    }

    @Override
    public long countTopicByName(String name) {
        return topicRepository.countByName(name);
    }

    @Override
    public long countTopicByManagerAndStatus(String statusName, String username) {
        TopicStatusDto statusDto = topicStatusService.getByName(statusName);
        TopicStatus statusEntity = statusMapper.dto2Entity(statusDto);
       return topicRepository.countTopicByManagerAndStatus(username,statusEntity);

    }

    @Override
    public long countTopicByManagerAndResult(String resultName, String username) {
        TopicResultDto resultDto = topicResultService.getResultByName(resultName);
        TopicResult resultEntity = resultMapper.dto2Entity(resultDto);
        return topicRepository.countTopicByManagerAndResult(username,resultEntity);
    }

    @Override
    public long countTopicByStatus(String statusName) {
        TopicStatusDto statusDto = topicStatusService.getByName(statusName);
        TopicStatus statusEntity = statusMapper.dto2Entity(statusDto);
        return topicRepository.countTopicByManagerAndStatus(null,statusEntity);
    }

    @Override
    public long countTopicByResult(String resultName) {
        TopicResultDto resultDto = topicResultService.getResultByName(resultName);
        TopicResult resultEntity = resultMapper.dto2Entity(resultDto);
        return topicRepository.countTopicByManagerAndResult(null,resultEntity);
    }

    @Override
    public boolean existByName(String name) {
        return topicRepository.existsByName(name);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public void updateTopic(Integer id, TopicDto topicRequest) {
        Topic oldTopic = GetEntityById.getEntity(topicRepository, id);

        oldTopic.setName(topicRequest.getName());
        oldTopic.setStartDate(topicRequest.getStartDate());
        oldTopic.setEndDate(topicRequest.getEndDate());
        oldTopic.setExpense(topicRequest.getExpense());

        TopicStatus statusOfTopicReq = statusMapper.dto2Entity(topicRequest.getTopicStatus());
        TopicResult resultOfTopicReq = resultMapper.dto2Entity(topicRequest.getTopicResult());

        oldTopic.setTopicStatus(statusOfTopicReq);
        if(resultOfTopicReq != null)
            oldTopic.setTopicResult(resultOfTopicReq);

        Topic newTopic = topicRepository.save(oldTopic);
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
    public Page<TopicDto> getTopics(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Topic> topicPageEntity = topicRepository.findAll(paging);
        return topicPageEntity.map(topicMapper::entity2Dto);
    }

    @Override
    public Page<TopicDto> getApprovedTopics(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        TopicStatus statusEntity = statusRepository.findFirstByTitle("Ch??a duy???t");
        Page<Topic> topicPageEntity = topicRepository.findByTopicStatusNot(statusEntity, paging);
        return topicPageEntity.map(topicMapper::entity2Dto);
    }

    @Override
    public Page<TopicDto> getFilteredApprovedTopics(int page, int size, String searchName, List<String> organFilter,
                                                    String searchManganer, String statusFilter) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");//t???o sau th?? hi???n th??? tr?????c
        Pageable paging = PageRequest.of(page, size, sort);  //n???u c???n c??? paging th?? pass sort v??o pageable

        TopicStatus statusEntity = statusRepository.findFirstByTitle("Ch??a duy???t");
        Page<Topic> topicPageEntity;
        if (searchName.equals("") && organFilter.size() == 0 && searchManganer.equals("") && statusFilter.equals("")) {
            topicPageEntity = topicRepository.findByTopicStatusNot(statusEntity, paging);
        } else {
            if (organFilter.size() == 0)
                topicPageEntity =
                        topicRepository.findByTopicStatusNotAndTopicStatus_TitleContainingAndNameContainingAndOrgan_NameContainingAndManagerContaining(statusEntity, statusFilter, searchName, "", searchManganer, paging);
            else topicPageEntity =
                    topicRepository.findByTopicStatusNotAndTopicStatus_TitleContainingAndNameContainingAndOrgan_NameInAndManagerContaining(statusEntity, statusFilter, searchName, organFilter, searchManganer, paging);
        }
        return topicPageEntity.map(topicMapper::entity2Dto);
    }

    @Override
    public List<TopicDto> getNonApprovedTopicsByOrgan(Integer organId) {
        Organ organEntity = GetEntityById.getEntity(organRepository, organId);
        TopicStatus statusEntity = statusRepository.findFirstByTitle("Ch??a duy???t"); // t???m th???i fix c???ng
//        List<Topic> topicListEntity = topicRepository.findByOrganAndTopicStatus(organEntity,statusEntity);
//        return topicMapper.listEntity2Dto(topicListEntity);
        return null;
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
        topicRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void softDeleteTopic(Integer id) {
        topicRepository.softDeleteTopicById(id);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public void approveTopic(Integer topicId) {
        Topic topicEntity = GetEntityById.getEntity(topicRepository, topicId);
        TopicStatus statusEntity = statusMapper.dto2Entity(topicStatusService.getByName(TopicStatusEnum.DA_PHE_DUYET.getValue()));
        topicEntity.setTopicStatus(statusEntity);
        topicRepository.save(topicEntity);
    }

    //deprecated
    public Topic getTopicEntity(Integer id) {
        //return entity if exist,else throw exception
        return topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "topic"));
    }
}
