package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileTypeDto;

public interface TopicFileTypeService {
    TopicFileTypeDto getTopicFileTypeById(Integer topicFileTypeId);
    TopicFileTypeDto getTopicFileTypeByTitle(String topicFileTypeTitle);
}
