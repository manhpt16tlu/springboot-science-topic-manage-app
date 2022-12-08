package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormFileDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileDto;
import sokhoahoccongnghe.phutho.gov.vn.exception.FileUploadException;
import sokhoahoccongnghe.phutho.gov.vn.service.*;

import java.io.IOException;
import java.util.Date;

@Service
public class UploadAndDownloadServiceImpl implements UploadAndDownloadService {

    @Autowired
    private TopicFileService topicFileService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private FormService formService;

    @Autowired
    private FormFileService formFileService;

    @Autowired
    private TopicFileTypeService topicFileTypeService;


    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public TopicFileDto uploadTopicFile(MultipartFile fileUpload, String topicFileType, Integer topicId) {
        if(fileUpload.isEmpty()) throw new FileUploadException("file upload can not be NULL");
        TopicFileDto fileNeedSave;
        try{
            //save to file system
            fileNeedSave = fileStorageService.saveTopicFile(fileUpload,topicFileType);
        }
        catch (IOException e){
            throw new FileUploadException("can not upload file",e);
        }
        fileNeedSave.setTopic(topicService.getTopic(topicId));
        fileNeedSave.setCreateDate(new Date());

        return  topicFileService.createTopicFile(fileNeedSave);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public FormFileDto uploadFormFile(MultipartFile fileUpload, Integer formId) {
        if(fileUpload.isEmpty()) throw new FileUploadException("file upload can not be NULL");
        FormDto formNeedUpload =  formService.getFormById(formId);
        FormFileDto fileNeedSave;
        try {
           fileNeedSave =  fileStorageService.saveFormFile(fileUpload,formNeedUpload);
        } catch (IOException e) {
            throw new FileUploadException("can not upload file",e);
        }
        fileNeedSave.setForm(formNeedUpload);
        fileNeedSave.setCreateDate(new Date());
        return formFileService.createFormFile(fileNeedSave);
    }

    @Override
    public Resource getFile(String fileType, String fileCode) {
     return fileStorageService.getFileAsResource(fileCode,fileType);
    }

//
//    @Override
//    public Resource getFile(String fileCode) {
//       if(!fileService.existsByFileCode(fileCode)) throw new FileDownLoadException("file " + fileCode + " not exist");
//       return fileStorageService.getFileAsResource(fileCode);
//    }
}
