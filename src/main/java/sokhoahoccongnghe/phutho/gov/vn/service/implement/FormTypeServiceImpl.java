package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormTypeDto;
import sokhoahoccongnghe.phutho.gov.vn.mapper.FormTypeMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.FormTypeRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.FormTypeService;

import java.util.List;

@Service
public class FormTypeServiceImpl implements FormTypeService {
    @Autowired
    private FormTypeRepository formTypeRepository;

    @Autowired
    private FormTypeMapper formTypeMapper;
    @Override
    public List<FormTypeDto> getAllFormType() {
        return formTypeMapper.listEntity2Dto(formTypeRepository.findAll());
    }
}
