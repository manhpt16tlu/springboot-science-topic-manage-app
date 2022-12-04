package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileDto;

public interface UploadAndDownloadService {
    TopicFileDto uploadTopicFile(MultipartFile fileUpload, String topicFileType, Integer topicId);
    Resource getFile(String fileType,String fileCode);

}
