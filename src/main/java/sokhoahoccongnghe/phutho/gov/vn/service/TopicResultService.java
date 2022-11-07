package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.TopicResultDto;

import java.util.List;

public interface TopicResultService {
    List<TopicResultDto> getAllResult();
    TopicResultDto createResult(TopicResultDto resultRequest);
    TopicResultDto getResult(Integer id);
    void deleteResult(Integer id);
    void updateResult(Integer id,TopicResultDto resultRequest);
}
