package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.web.multipart.MultipartFile;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;

import java.io.IOException;

public interface FileStorageService {
    FileDto save(MultipartFile multipartFile,String fileType) throws IOException;
}
