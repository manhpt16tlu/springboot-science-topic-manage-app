package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicStatusDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicStatus;
import sokhoahoccongnghe.phutho.gov.vn.exception.NotFoundException;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicStatusMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.TopicStatusRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicStatusService;

import java.util.List;

@Service
public class TopicStatusServiceImpl implements TopicStatusService {
    @Autowired
    private TopicStatusRepository statusRepository;

    @Autowired
    private TopicStatusMapper statusMapper;

    @Override
    public TopicStatusDto createStatus(TopicStatusDto statusRequest) {
        TopicStatus statusCreated = statusRepository.save(statusMapper.dto2Entity(statusRequest));
        return statusMapper.entity2Dto(statusCreated);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public void deleteStatus(Integer id) {
        TopicStatus statusEntity = this.getStatusEntity(id);
        statusRepository.delete(statusEntity);
    }

    @Override
    public void updateStatus(Integer id,TopicStatusDto statusRequest) {
        TopicStatus statusEntity = this.getStatusEntity(id);
        statusEntity.setTitle(statusRequest.getTitle());
        statusEntity.setDescription(statusRequest.getDescription());
        statusRepository.save(statusEntity);
    }


    @Override
    public List<TopicStatusDto> getAllStatus() {
        List<TopicStatus> statusListEntity = statusRepository.findAll();
        return statusMapper.listEntity2Dto(statusListEntity);
    }

    @Override
    public TopicStatusDto getStatus(Integer id) {
        TopicStatus statusEntity = this.getStatusEntity(id);
        return statusMapper.entity2Dto(statusEntity);
    }

    private TopicStatus getStatusEntity(Integer id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "topic status"));
    }
}
