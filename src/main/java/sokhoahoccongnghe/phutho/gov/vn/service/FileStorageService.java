package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileDto;

import java.io.IOException;

//handle logic in file system , not in database
public interface FileStorageService {
    TopicFileDto saveTopicFile(MultipartFile multipartFile,String topicFileType) throws IOException;
    Resource getFileAsResource(String fileCode,String fileType);
//    boolean checkExistInFileSystem(String fileName);
}
