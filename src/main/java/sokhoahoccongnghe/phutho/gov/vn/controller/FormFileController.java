package sokhoahoccongnghe.phutho.gov.vn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageEnum;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.FormFileService;

@RestController
@RequestMapping(value = {"/api/formFile"})
@CrossOrigin(origins = "http://localhost:3000")
public class FormFileController {
    @Autowired
    private FormFileService formFileService;

    @GetMapping(value = "/{formId}")
    public ResponseEntity<Object> getFormFileByFormId(@PathVariable Integer formId){
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
               formFileService.getByFormId(formId), true);
    }
}
