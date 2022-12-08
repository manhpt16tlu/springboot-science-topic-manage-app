package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.data.domain.Page;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormDto;

public interface FormService {
    FormDto createForm(FormDto formRequest);
    long countFormByName(String name);
    FormDto getFormById(Integer formId);
    void deleteById(Integer formId);
    Page<FormDto> getAllForm(int page,int size);
}
