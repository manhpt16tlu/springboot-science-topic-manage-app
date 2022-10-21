package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.dto.OrganDto;
import sokhoahoccongnghe.phutho.gov.vn.mapper.OrganMapper;
import sokhoahoccongnghe.phutho.gov.vn.service.OrganService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/organ")
public class OrganController {
    @Autowired
    private OrganMapper organMapper;
    @Autowired
    private OrganService organService;
    @PostMapping
    public ResponseEntity<OrganDto> createOrgan(@RequestBody OrganDto organRequest) {
        OrganDto organCreated = organService.createOrgan(organRequest);
        return new ResponseEntity<>(organCreated, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<OrganDto>> getOrgans(){
        return new ResponseEntity<>(organService.getOrgans(),HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrganDto> getOrgan(@PathVariable(value = "id") Integer id){
        OrganDto o = organService.getOrgan(id);
        return new ResponseEntity<>(o,HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOrgan(@PathVariable(value = "id") Integer id){
        organService.deleteOrgan(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateOrgan(@PathVariable(value = "id") Integer id,@RequestBody OrganDto organRequest){
        organService.updateOrgan(id,organRequest);
        return ResponseEntity.noContent().build();
    }
}
