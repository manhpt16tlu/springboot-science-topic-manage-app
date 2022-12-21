package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.TopicStatusDto;

import java.util.List;

public interface TopicStatusService {
    List<TopicStatusDto> getAllStatus();
    TopicStatusDto getStatus(Integer id);
    TopicStatusDto createStatus(TopicStatusDto statusRequest);
    void deleteStatus(Integer id);
    void updateStatus(Integer id,TopicStatusDto statusRequest);
    TopicStatusDto getByName(String name);
}
