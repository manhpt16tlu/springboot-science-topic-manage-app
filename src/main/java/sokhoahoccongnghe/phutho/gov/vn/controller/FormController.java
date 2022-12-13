package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormDto;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageEnum;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.FormService;

@RestController
@RequestMapping(value = {"/api/form"})
@CrossOrigin(origins = "http://localhost:3000")
public class FormController {
    @Autowired
    private FormService formService;

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "7") int size) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                formService.getAllForm(page,size),true);
    }

    @PostMapping
    public ResponseEntity<Object> createForm(@RequestBody FormDto formRequest) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                formService.createForm(formRequest) ,
                true);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteForm(@PathVariable Integer id) {
        formService.deleteById(id);
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                null, true);
    }
    @GetMapping(value = "/countByName")
    public ResponseEntity<Object> countFormByName(@RequestParam String name){
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                formService.countFormByName(name), true);
    }
}
