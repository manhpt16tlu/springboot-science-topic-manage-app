package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormFileDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.UserAvatarFileDto;

//handle operation both database and file system
public interface UploadAndDownloadService {
    TopicFileDto uploadTopicFile(MultipartFile fileUpload, String topicFileType, Integer topicId);
    FormFileDto uploadFormFile(MultipartFile fileUpload, Integer formId);
    UserAvatarFileDto uploadAvatarFile(MultipartFile fileUpload);
    Resource getFile(String fileType,String fileCode);
    void deleteAvatar(String username);
    void deleteForm(Integer formId);

}
