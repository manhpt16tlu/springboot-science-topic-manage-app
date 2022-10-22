package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFieldDto;

import java.util.List;

public interface TopicFieldService {
    TopicFieldDto createField(TopicFieldDto fieldRequest);
    List<TopicFieldDto> getFields();
    void deleteField(Integer id);
    TopicFieldDto getField(Integer id);
    void updateField(Integer id,TopicFieldDto fieldRequest);
}
