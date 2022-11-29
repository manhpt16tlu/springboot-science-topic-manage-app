package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.File;
import sokhoahoccongnghe.phutho.gov.vn.mapper.FileMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.FileRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.FileService;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<FileDto> getAllFiles() {
        return fileMapper.listEntity2Dto(fileRepository.findAll());
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
