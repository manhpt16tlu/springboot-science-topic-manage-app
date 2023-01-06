package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sokhoahoccongnghe.phutho.gov.vn.dto.*;
import sokhoahoccongnghe.phutho.gov.vn.exception.FileDeleteException;
import sokhoahoccongnghe.phutho.gov.vn.exception.FileUploadException;
import sokhoahoccongnghe.phutho.gov.vn.mapper.UserMapper;
import sokhoahoccongnghe.phutho.gov.vn.model.FileTypeEnum;
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

    @Autowired
    private UserAvatarFileService avatarFileService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public TopicFileDto uploadTopicFile(MultipartFile fileUpload, String topicFileType, Integer topicId) {
        //check file upload k tồn tại hoặc không có nội dung
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

    @Override
    @Transactional
    public void deleteAvatar(String username) {
        //remove from database
        UserAvatarFileDto deletedAvatarFile  = avatarFileService.removeAvatar(username);
        //remove from file system
        try {
            fileStorageService.deleteFile(deletedAvatarFile.getFileName(), FileTypeEnum.AVATAR.getValue());
        } catch (IOException e) {
            throw new FileDeleteException("can not delete file", e);
        }
    }

    @Override
    @Transactional
    public void deleteForm(Integer formId) {
        String deletedFileName = formService.deleteById(formId);
        try {
            fileStorageService.deleteFile(deletedFileName, FileTypeEnum.FORM.getValue());
        } catch (IOException e) {
            throw new FileDeleteException("can not delete file", e);
        }
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public UserAvatarFileDto uploadAvatarFile(MultipartFile fileUpload) {
        if(fileUpload.isEmpty()) throw new FileUploadException("file upload can not be NULL");
        UserAvatarFileDto fileNeedSave;
        UserDetails currentUser = userService.getPrincipal();
        if(avatarFileService.checkExistByUser(currentUser.getUsername())){
            deleteAvatar(currentUser.getUsername());
        }

        try {
            fileNeedSave = fileStorageService.saveAvatarFile(fileUpload,currentUser.getUsername());
        } catch (IOException e) {
            throw new FileUploadException("can not upload file",e);
        }

        UserDto userDto = userService.getUserByUsername(currentUser.getUsername());
        fileNeedSave.setUser(userDto);
        fileNeedSave.setCreateDate(new Date());
        return avatarFileService.createAvatarFile(fileNeedSave);
    }

    //
//    @Override
//    public Resource getFile(String fileCode) {
//       if(!fileService.existsByFileCode(fileCode)) throw new FileDownLoadException("file " + fileCode + " not exist");
//       return fileStorageService.getFileAsResource(fileCode);
//    }
}
