package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormFileDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.FormFile;
import sokhoahoccongnghe.phutho.gov.vn.mapper.FormFileMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.FormFileRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.FormFileService;

@Service
public class FormFileServiceImpl implements FormFileService {

    @Autowired
    private FormFileRepository formFileRepository;

    @Autowired
    private FormFileMapper formFileMapper;
    @Override
    public FormFileDto createFormFile(FormFileDto formFileRequest) {
        FormFile formFileRequestEntity = formFileMapper.dto2Entity(formFileRequest);
        return formFileMapper.entity2Dto(formFileRepository.save(formFileRequestEntity));
    }

    @Override
    public boolean checkExistByFileCode(String fileCode) {
        return formFileRepository.existsById(fileCode);
    }

    @Override
    public FormFileDto getByFormId(Integer formId) {
        return formFileMapper.entity2Dto(formFileRepository.findFirstByFormId(formId));
    }
}
