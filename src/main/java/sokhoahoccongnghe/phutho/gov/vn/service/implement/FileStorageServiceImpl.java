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
import sokhoahoccongnghe.phutho.gov.vn.dto.*;
import sokhoahoccongnghe.phutho.gov.vn.exception.FileDownLoadException;
import sokhoahoccongnghe.phutho.gov.vn.model.FileTypeEnum;
import sokhoahoccongnghe.phutho.gov.vn.service.*;
import sokhoahoccongnghe.phutho.gov.vn.util.VietnamStringNormalize;
import sokhoahoccongnghe.phutho.gov.vn.view.TopicFileTypeView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private TopicFileTypeService topicFileTypeService;

    @Autowired
    private TopicFileService topicFileService;

    @Autowired
    private FormFileService formFileService;

    @Autowired
    private UserAvatarFileService avatarFileService;

    private final Path topicRootFolder = Paths.get("store/files/topic");
    private final Path formRootFolder = Paths.get("store/files/form");
    private final Path avatarRootFolder = Paths.get("store/files/avatar");

    @Override
    @Transactional(rollbackFor = {IOException.class})
    public TopicFileDto saveTopicFile(MultipartFile multipartFile, String topicFileType) throws IOException {

        TopicFileDto fileNeedSave = new TopicFileDto();

        //làm thủ công do gặp lỗi
        TopicFileTypeView topicFileTypeView = topicFileTypeService.getTopicFileTypeViewByTitle(topicFileType);
        TopicFileTypeDto topicFileTypeNeedSave = new TopicFileTypeDto();
        topicFileTypeNeedSave.setId(topicFileTypeView.getId());
        topicFileTypeNeedSave.setTitle(topicFileTypeView.getTitle());
        topicFileTypeNeedSave.setCreateDate(topicFileTypeView.getCreateDate());
        topicFileTypeNeedSave.setDescription(topicFileTypeView.getDescript());

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
                    VietnamStringNormalize.normalize(org.apache.commons.lang3.StringUtils.deleteWhitespace(topicFileTypeNeedSave.getTitle().toLowerCase()))+"_"+ getLocalDateNow() +
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
            if(!formFileService.checkExistByFileCode(fileCode))
                throw new FileDownLoadException("file " + fileCode + " not exist");
            try {
                Files.list(formRootFolder).forEach(file -> {
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
        else if(fileType.equals(FileTypeEnum.AVATAR.getValue())){
            if(!avatarFileService.checkExistByFileCode(fileCode))
                throw new FileDownLoadException("file " + fileCode + " not exist");
            try {
                Files.list(avatarRootFolder).forEach(file -> {
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
        else
            throw new FileDownLoadException("this file type does not exist:"+fileType);
    }

    @Override
    public boolean deleteFile(String fileServerName,String fileType) throws IOException {
        Path filePath;
        if(fileType.equals(FileTypeEnum.AVATAR.getValue())){
            filePath = avatarRootFolder.resolve(fileServerName);
        }
        else
            filePath = formRootFolder.resolve(fileServerName);
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
                    VietnamStringNormalize.normalize(org.apache.commons.lang3.StringUtils.deleteWhitespace(formNeedUpload.getType().getTitle().toLowerCase()))+"_"+ getLocalDateNow() +
                    "."+fileExtension;
            fileNeedSave.setServerName(fileServerName);
            Path filePath = formRootFolder.resolve(fileServerName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        return fileNeedSave;
    }

    @Override
    public UserAvatarFileDto saveAvatarFile(MultipartFile multipartFile,String username) throws IOException {
        UserAvatarFileDto fileNeedSave = new UserAvatarFileDto();

        String originFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String fileExtension = FilenameUtils.getExtension(originFileName);

//        fileNeedSave.setOriginName(originFileName);
        fileNeedSave.setSize(multipartFile.getSize());

        String fileCode;
        do {
            fileCode = RandomStringUtils.randomAlphanumeric(8);
        } while (avatarFileService.checkExistByFileCode(fileCode));

        fileNeedSave.setCode(fileCode);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            String fileServerName = fileCode+"_"+
                    VietnamStringNormalize.normalize(org.apache.commons.lang3.StringUtils.deleteWhitespace(username))+"_"+ getLocalDateNow() +
                    "."+fileExtension;
            fileNeedSave.setFileName(fileServerName);
            Path filePath = avatarRootFolder.resolve(fileServerName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        return fileNeedSave;
    }

    private LocalDate getLocalDateNow(){
        ZoneId zoneId = ZoneId.of("VST",ZoneId.SHORT_IDS);
        return LocalDate.now(zoneId);
    }
//    @Override
//    public boolean checkExistInFileSystem(String fileName) {
//        Path filePath = rootFolder.resolve(fileName);
//        return Files.exists(filePath);
//    }
}
