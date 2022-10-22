package sokhoahoccongnghe.phutho.gov.vn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFieldDto;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicFieldService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/field")
public class TopicFieldController {
    @Autowired
    private TopicFieldService topicFieldService;
    @PostMapping
    public ResponseEntity<TopicFieldDto> createField(@RequestBody TopicFieldDto fieldRequest) {
        TopicFieldDto fieldCreated = topicFieldService.createField(fieldRequest);
        return new ResponseEntity<>(fieldCreated, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<TopicFieldDto>> getFields(){
        return new ResponseEntity<>(topicFieldService.getFields(),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteField(@PathVariable Integer id){
        topicFieldService.deleteField(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<TopicFieldDto> getField(@PathVariable(value = "id") Integer id){
        TopicFieldDto fieldFinded = topicFieldService.getField(id);
        return new ResponseEntity<>(fieldFinded,HttpStatus.OK);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity updateField(@PathVariable(value = "id") Integer id,@RequestBody TopicFieldDto fieldRequest){
        topicFieldService.updateField(id,fieldRequest);
        return ResponseEntity.noContent().build();
    }
}
