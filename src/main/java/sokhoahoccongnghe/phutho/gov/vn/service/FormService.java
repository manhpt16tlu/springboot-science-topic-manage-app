package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.data.domain.Page;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormDto;

public interface FormService {
    FormDto createForm(FormDto formRequest);
    boolean existByName(String name);
    FormDto getFormById(Integer formId);
    String deleteById(Integer formId);
    Page<FormDto> getAllForm(int page,int size,String formName,String formType);
}
