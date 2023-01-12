package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormFileDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Form;
import sokhoahoccongnghe.phutho.gov.vn.entity.FormType;
import sokhoahoccongnghe.phutho.gov.vn.mapper.FormMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.FormRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.FormFileService;
import sokhoahoccongnghe.phutho.gov.vn.service.FormService;
import sokhoahoccongnghe.phutho.gov.vn.util.GetEntityById;

import java.util.Date;

@Service
public class FormServiceImpl implements FormService {
    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FormFileService formFileService;

    @Autowired
    private FormMapper formMapper;
    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public FormDto createForm(FormDto formRequest) {
        Form formRequestEntity = formMapper.dto2Entity(formRequest);
        formRequestEntity.setCreateDate(new Date());
        return formMapper.entity2Dto(formRepository.save(formRequestEntity));
    }

    @Override
    public boolean existByName(String name) {
        return formRepository.existsByName(name);
    }

    @Override
    public FormDto getFormById(Integer formId) {
        return formMapper.entity2Dto(GetEntityById.getEntity(formRepository,formId));
    }

    @Override
    @Transactional
    public String deleteById(Integer formId) {
        Form formNeedDelete = GetEntityById.getEntity(formRepository,formId);
        FormFileDto formFileNeedDelete = formFileService.getByFormId(formNeedDelete.getId());
        formRepository.delete(formNeedDelete);
        if(formFileNeedDelete!=null) return formFileNeedDelete.getServerName();
        return null;
    }

    @Override
    public Page<FormDto> getAllForm(int page, int size,String formName,String formType) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createDate");
        Pageable paging = PageRequest.of(page,size,sort);

        Gson gson = new Gson();
        FormType formTypeEntity = gson.fromJson(formType,FormType.class);
        Page<Form> formPageEntity = formRepository.getAll(formName,formTypeEntity,paging);
        return formPageEntity.map(formMapper::entity2Dto);
    }
}
