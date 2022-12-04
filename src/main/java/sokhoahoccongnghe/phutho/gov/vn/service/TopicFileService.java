package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileDto;
import sokhoahoccongnghe.phutho.gov.vn.view.TopicFileView;

import java.util.List;

public interface TopicFileService {
    List<TopicFileDto> getAllTopicFiles();
    TopicFileDto createTopicFile(TopicFileDto topicFileRequest);
    TopicFileView getTopicFileByTopicIdAndTopicFileType(Integer topicId, String topicFileType);
    boolean checkExistByFileCode(String fileCode);
}
