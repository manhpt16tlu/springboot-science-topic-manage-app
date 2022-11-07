package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.dto.OrganDto;
import sokhoahoccongnghe.phutho.gov.vn.mapper.OrganMapper;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageModel;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.OrganService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/organ")
@CrossOrigin(origins = "http://localhost:3000")
public class OrganController {

    @Autowired
    private OrganService organService;

    @PostMapping
    public ResponseEntity<Object> createOrgan(@RequestBody OrganDto organRequest) {
        OrganDto organCreated = organService.createOrgan(organRequest);
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK, organCreated,
                true);
    }

    @GetMapping
    public ResponseEntity<Object> getOrgans(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue =
            "5") int size,@RequestParam(defaultValue = "") String search) {
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                organService.getOrgans(page,size,search), true);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrganDto> getOrgan(@PathVariable(value = "id") Integer id) {
        OrganDto o = organService.getOrgan(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOrgan(@PathVariable(value = "id") Integer id) {
        organService.deleteOrgan(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateOrgan(@PathVariable(value = "id") Integer id, @RequestBody OrganDto organRequest) {
        organService.updateOrgan(id, organRequest);
        return ResponseEntity.noContent().build();
    }
}
