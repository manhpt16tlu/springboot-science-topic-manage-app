package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileTypeDto;
import sokhoahoccongnghe.phutho.gov.vn.mapper.FileTypeMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.FileTypeRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.FileTypeService;

import java.util.List;

@Service
public class FileTypeServiceImpl implements FileTypeService {
    @Autowired
    private FileTypeRepository fileTypeRepository;
    @Autowired
    private FileTypeMapper fileTypeMapper;
    @Override
    public List<FileTypeDto> getAllTypes() {
        return fileTypeMapper.listEntity2Dto(fileTypeRepository.findAll());
    }
}
