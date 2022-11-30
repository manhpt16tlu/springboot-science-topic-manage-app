package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileTypeDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;
import sokhoahoccongnghe.phutho.gov.vn.exception.FileDownLoadException;
import sokhoahoccongnghe.phutho.gov.vn.exception.FileUploadException;
import sokhoahoccongnghe.phutho.gov.vn.service.*;

import java.io.IOException;
import java.util.Date;

@Service
public class UploadAndDownloadServiceImpl implements UploadAndDownloadService {

    @Autowired
    private FileService fileService;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private FileTypeService fileTypeService;

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public FileDto upload(MultipartFile fileUpload,String fileType,String fileTitle,Integer topicId) {
        if(fileUpload.isEmpty()) throw new FileUploadException("file upload can not be NULL");
        FileDto fileNeedSave;
        try {
            fileNeedSave = fileStorageService.save(fileUpload,fileType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadException("can not upload file",e);
        }

        fileNeedSave.setTitle(fileTitle);

        if(topicId != null) {
            TopicDto topicDto = topicService.getTopic(topicId);
            fileNeedSave.setTopic(topicDto);
        }

        FileTypeDto fileTypeDto = fileTypeService.getFileTypeByName(fileType);
        if(fileTypeDto == null)
            fileTypeDto = fileTypeService.getFileTypeByName("Không xác định");

        fileNeedSave.setType(fileTypeDto);

        fileNeedSave.setCreateDate(new Date());

        return fileService.save(fileNeedSave);
    }

    @Override
    public Resource getFile(String fileCode) {
       if(!fileService.existsByFileCode(fileCode)) throw new FileDownLoadException("file " + fileCode + " not exist");
       return fileStorageService.getFileAsResource(fileCode);
    }
}
