package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicResultDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicResult;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicResultMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.TopicResultRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicResultService;
import sokhoahoccongnghe.phutho.gov.vn.util.GetEntityById;

import java.util.List;

@Service
public class TopicResultServiceImpl implements TopicResultService {

    @Autowired
    private TopicResultRepository resultRepository;

    @Autowired
    private TopicResultMapper resultMapper;

    @Override
    public List<TopicResultDto> getAllResult() {
        List<TopicResult> listResultEntity = resultRepository.findAll();
        return resultMapper.listEntity2Dto(listResultEntity);
    }

    @Override
    public TopicResultDto createResult(TopicResultDto resultRequest) {
        TopicResult resultCreated = resultRepository.save(resultMapper.dto2Entity(resultRequest));
        return resultMapper.entity2Dto(resultCreated);
    }

    @Override
    public TopicResultDto getResult(Integer id) {
        TopicResult resultEntity = GetEntityById.getEntity(resultRepository,id);
        return resultMapper.entity2Dto(resultEntity);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public void deleteResult(Integer id) {
        TopicResult resultEntity = GetEntityById.getEntity(resultRepository,id);
        resultRepository.delete(resultEntity);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public void updateResult(Integer id, TopicResultDto resultRequest) {
        TopicResult resultEntity = GetEntityById.getEntity(resultRepository,id);
        resultEntity.setTitle(resultRequest.getTitle());
        resultEntity.setDescription(resultRequest.getDescription());
        resultRepository.save(resultEntity);
    }

    @Override
    public TopicResultDto getResultByName(String name) {
        return resultMapper.entity2Dto(resultRepository.findByTitle(name));
    }
}
