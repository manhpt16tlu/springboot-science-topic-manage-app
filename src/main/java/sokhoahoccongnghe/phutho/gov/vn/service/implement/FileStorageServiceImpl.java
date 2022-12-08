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
import sokhoahoccongnghe.phutho.gov.vn.dto.FormDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormFileDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileTypeDto;
import sokhoahoccongnghe.phutho.gov.vn.exception.FileDownLoadException;
import sokhoahoccongnghe.phutho.gov.vn.model.FileTypeEnum;
import sokhoahoccongnghe.phutho.gov.vn.service.FileStorageService;
import sokhoahoccongnghe.phutho.gov.vn.service.FormFileService;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicFileService;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicFileTypeService;
import sokhoahoccongnghe.phutho.gov.vn.util.VietnamStringNormalize;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private TopicFileTypeService topicFileTypeService;

    @Autowired
    private TopicFileService topicFileService;

    @Autowired
    private FormFileService formFileService;

    private final Path topicRootFolder = Paths.get("store/files/topic");
    private final Path formRootFolder = Paths.get("store/files/form");

    @Override
    @Transactional(rollbackFor = {IOException.class})
    public TopicFileDto saveTopicFile(MultipartFile multipartFile, String topicFileType) throws IOException {

        TopicFileDto fileNeedSave = new TopicFileDto();
        TopicFileTypeDto topicFileTypeNeedSave = topicFileTypeService.getTopicFileTypeByTitle(topicFileType);
        fileNeedSave.setType(topicFileTypeNeedSave);

        String originFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String fileExtension = FilenameUtils.getExtension(originFileName);


        fileNeedSave.setOriginName(originFileName);
        fileNeedSave.setSize(multipartFile.getSize());

        String fileCode;
        do {
            fileCode = RandomStringUtils.randomAlphanumeric(8);
        } while (topicFileService.checkExistByFileCode(fileCode));

        fileNeedSave.setCode(fileCode);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            String fileServerName = fileCode+"_"+
                    VietnamStringNormalize.normalize(org.apache.commons.lang3.StringUtils.deleteWhitespace(topicFileTypeNeedSave.getTitle().toLowerCase()))+
                    "."+fileExtension;
            fileNeedSave.setServerName(fileServerName);
            Path filePath = topicRootFolder.resolve(fileServerName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        return fileNeedSave;
    }

    @Override
    public Resource getFileAsResource(String fileCode,String fileType) {
        Path[] fileNeedDownloadArr = {null}; // use array because can not modify local variable in lambda
        if (fileType.equals(FileTypeEnum.TOPIC.getValue())) {
            if(!topicFileService.checkExistByFileCode(fileCode))
                throw new FileDownLoadException("file " + fileCode + " not exist");
            try {
                Files.list(topicRootFolder).forEach(file -> {
//                System.out.println(file.toString());
                    if (file.getFileName().toString().startsWith(fileCode)) {
                        fileNeedDownloadArr[0] = file;
                    }
                });
                if (fileNeedDownloadArr[0] != null) {
                    return new UrlResource(fileNeedDownloadArr[0].toUri());
                } else throw new FileDownLoadException("file " + fileCode + " not exist");
            } catch (IOException e) {
                e.printStackTrace();
                throw new FileDownLoadException("can not download file", e);
            }
        }
        else if(fileType.equals(FileTypeEnum.FORM.getValue())){
            return null;
        }
        else
            throw new FileDownLoadException("this file type does not exist:"+fileType);
    }

    @Override
    public boolean deleteFile(String fileServerName) throws IOException {
       Path filePath = formRootFolder.resolve(fileServerName);
       return Files.deleteIfExists(filePath);
    }


    @Override
    public FormFileDto saveFormFile(MultipartFile multipartFile, FormDto formNeedUpload) throws IOException {
        FormFileDto fileNeedSave = new FormFileDto();

        String originFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String fileExtension = FilenameUtils.getExtension(originFileName);

        fileNeedSave.setOriginName(originFileName);
        fileNeedSave.setSize(multipartFile.getSize());

        String fileCode;
        do {
            fileCode = RandomStringUtils.randomAlphanumeric(8);
        } while (formFileService.checkExistByFileCode(fileCode));

        fileNeedSave.setCode(fileCode);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            String fileServerName = fileCode+"_"+
                    VietnamStringNormalize.normalize(org.apache.commons.lang3.StringUtils.deleteWhitespace(formNeedUpload.getType().getTitle().toLowerCase()))+
                    "."+fileExtension;
            fileNeedSave.setServerName(fileServerName);
            Path filePath = formRootFolder.resolve(fileServerName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        return fileNeedSave;
    }

//    @Override
//    public boolean checkExistInFileSystem(String fileName) {
//        Path filePath = rootFolder.resolve(fileName);
//        return Files.exists(filePath);
//    }
}
