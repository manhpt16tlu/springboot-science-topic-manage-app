package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.web.multipart.MultipartFile;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;

public interface UploadAndDownloadService {
    FileDto upload(MultipartFile fileUpload,String fileType,String fileTitle,Integer topicId);
}