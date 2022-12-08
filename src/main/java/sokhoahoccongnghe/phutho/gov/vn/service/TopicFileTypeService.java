package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileTypeDto;
import sokhoahoccongnghe.phutho.gov.vn.view.TopicFileTypeView;

public interface TopicFileTypeService {
    TopicFileTypeDto getTopicFileTypeById(Integer topicFileTypeId);
    TopicFileTypeView getTopicFileTypeViewByTitle(String topicFileTypeTitle);
}
