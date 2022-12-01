package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.File;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;
import sokhoahoccongnghe.phutho.gov.vn.mapper.FileMapper;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.FileRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.FileService;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicService;
import sokhoahoccongnghe.phutho.gov.vn.util.GetEntityById;
import sokhoahoccongnghe.phutho.gov.vn.view.FileOfTopicView;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<FileDto> getAllFiles() {
        return fileMapper.listEntity2Dto(fileRepository.findAll());
    }

    @Override
    public List<FileOfTopicView> getFilesOfTopic(Integer topicId) {
        return fileRepository.findByTopic(topicId);
    }

    @Override
    public boolean existsByFileCode(String fileCode) {
        return fileRepository.existsById(fileCode);
    }

    @Override
    public FileDto getFile(String fileCode) {
        return null;
    }

    @Override
    public FileDto save(FileDto fileDto) {
        File savedFileEntity = fileRepository.save(fileMapper.dto2Entity(fileDto));
        return fileMapper.entity2Dto(savedFileEntity);
    }
}
