package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormFileDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileDto;

import java.io.IOException;

//handle logic in file system , not in database
public interface FileStorageService {
    TopicFileDto saveTopicFile(MultipartFile multipartFile,String topicFileType) throws IOException;
    FormFileDto saveFormFile(MultipartFile multipartFile, FormDto formNeedUpload) throws IOException;
    Resource getFileAsResource(String fileCode,String fileType);
    boolean deleteFile(String fileServerName) throws IOException;
//    boolean checkExistInFileSystem(String fileName);
}
