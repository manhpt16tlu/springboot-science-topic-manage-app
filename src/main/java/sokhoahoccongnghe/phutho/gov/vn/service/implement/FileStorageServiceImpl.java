package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;
import sokhoahoccongnghe.phutho.gov.vn.exception.FileDownLoadException;
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

    private final Path rootFolder = Paths.get("store/files");

    @Override
    @Transactional(rollbackFor = {IOException.class})
    public FileDto save(MultipartFile multipartFile, String fileType) throws IOException {
        String convertType;
        switch (fileType) {
            case "Đề cương":
                convertType = "decuong";
                break;
            case "Biểu mẫu":
                convertType = "bieumau";
                break;
            default:
                convertType = "unknown";
        }

        FileDto fileNeedSave = new FileDto();

        String originFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String fileExtension = FilenameUtils.getExtension(originFileName);

//        System.out.println(multipartFile.getContentType() + "-" + fileExtension);

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
            Path filePath = rootFolder.resolve(fileServerName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        return fileNeedSave;
    }

    @Override
    public Resource getFileAsResource(String fileCode) {
        Path[] fileNeedDownloadArr = {null}; // use array because can not modify local variable in lambda
        try {
            Files.list(rootFolder).forEach(file-> {
//                System.out.println(file.toString());
               if(file.getFileName().toString().startsWith(fileCode)){
                   fileNeedDownloadArr[0] = file;
               }
            });
            if(fileNeedDownloadArr[0] != null){
                return new UrlResource(fileNeedDownloadArr[0].toUri());
            }
            else throw new FileDownLoadException("file "+fileCode+" not exist");
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileDownLoadException("can not download file",e);
        }
    }
    @Override
    public boolean checkExistInFileSystem(String fileName) {
        Path filePath = rootFolder.resolve(fileName);
        return Files.exists(filePath);
    }
}
