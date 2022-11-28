package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;
import sokhoahoccongnghe.phutho.gov.vn.exception.FileUploadException;
import sokhoahoccongnghe.phutho.gov.vn.mapper.FileTypeMapper;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.FileTypeRepository;
import sokhoahoccongnghe.phutho.gov.vn.repository.TopicRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.FileService;
import sokhoahoccongnghe.phutho.gov.vn.service.FileStorageService;
import sokhoahoccongnghe.phutho.gov.vn.service.UploadAndDownloadService;
import sokhoahoccongnghe.phutho.gov.vn.util.GetEntityById;

import java.io.IOException;

@Service
public class UploadAndDownloadServiceImpl implements UploadAndDownloadService {

    @Autowired
    private FileService fileService;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private FileTypeRepository fileTypeRepository;
    @Autowired
    private FileTypeMapper fileTypeMapper;

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public FileDto upload(MultipartFile fileUpload,String fileType,String fileTitle,Integer topicId) {
        FileDto fileNeedSave;
        try {
            fileNeedSave = fileStorageService.save(fileUpload,fileType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadException("can not upload file",e);
        }
        if(fileTitle != null) fileNeedSave.setTitle(fileTitle);
        if(topicId != null) {
            Topic topicEntity = GetEntityById.getEntity(topicRepository,topicId);
            fileNeedSave.setTopic(topicMapper.entity2Dto(topicEntity));
        }
        fileNeedSave.setType(fileTypeMapper.entity2Dto(fileTypeRepository.findFirstByName(fileType)));
        return fileService.save(fileNeedSave);
    }
}
