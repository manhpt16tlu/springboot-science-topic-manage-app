package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;
import sokhoahoccongnghe.phutho.gov.vn.repository.FileRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.FileStorageService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Autowired
    private FileRepository fileRepository;

    private final Path root = Paths.get("store/files");
    @Override
    public FileDto save(MultipartFile multipartFile, String fileType) throws IOException {
        String convertType;
        switch (fileType) {
            case "Đề cương":
                convertType = "topic";
                break;
            case "Biểu mẫu":
                convertType = "form";
                break;
            default:
                convertType = "unknown";
        }

        FileDto fileNeedSave = new FileDto();
        String originFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String fileExtension = FilenameUtils.getExtension(originFileName);
        fileNeedSave.setOriginName(originFileName);
        fileNeedSave.setSize(multipartFile.getSize());
        String fileCode;
        do {
            fileCode = RandomStringUtils.randomAlphanumeric(8);
        } while (fileRepository.existsById(fileCode));
        fileNeedSave.setCode(fileCode);
        try (InputStream inputStream = multipartFile.getInputStream()) {
            String fileServerName = fileCode+"-"+convertType+"."+fileExtension;
            fileNeedSave.setServerName(fileServerName);
            Path filePath = root.resolve(fileServerName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        return fileNeedSave;
    }
}
