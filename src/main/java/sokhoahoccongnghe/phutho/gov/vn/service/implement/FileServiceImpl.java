package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.File;
import sokhoahoccongnghe.phutho.gov.vn.mapper.FileMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.FileRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.FileService;
import sokhoahoccongnghe.phutho.gov.vn.service.FileStorageService;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicService;
import sokhoahoccongnghe.phutho.gov.vn.view.FileOfTopicView;
import sokhoahoccongnghe.phutho.gov.vn.view.FormFileView;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<FileDto> getAllFiles() {
        return fileMapper.listEntity2Dto(fileRepository.findAll());
    }

    @Override
    public List<FileOfTopicView> getFilesOfTopic(Integer topicId) {
        List<FileOfTopicView> files = fileRepository.findByTopic(topicId);
        //check file tồn tại
        List<FileOfTopicView> realFiles = files.stream().filter(file->
                fileStorageService.checkExistInFileSystem(file.getServerName())
        ).collect(Collectors.toList());
        return realFiles;
    }

    @Override
    public List<FormFileView> getFilesByTypeName(String typeName) {
         return  fileRepository.findByFileType(typeName);
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
