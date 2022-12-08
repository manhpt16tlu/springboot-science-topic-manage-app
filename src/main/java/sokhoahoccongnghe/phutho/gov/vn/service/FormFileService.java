package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.FormFileDto;

public interface FormFileService {
    FormFileDto createFormFile(FormFileDto formFileRequest);
    boolean checkExistByFileCode(String fileCode);
    FormFileDto getByFormId(Integer formId);
}
