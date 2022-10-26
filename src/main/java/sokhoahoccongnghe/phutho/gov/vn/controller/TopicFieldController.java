package sokhoahoccongnghe.phutho.gov.vn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFieldDto;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageModel;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicFieldService;

@RestController
@RequestMapping(value = "/api/field")
public class TopicFieldController {
    @Autowired
    private TopicFieldService topicFieldService;

    @PostMapping
    public ResponseEntity<Object> createField(@RequestBody TopicFieldDto fieldRequest) {
        TopicFieldDto fieldCreated = topicFieldService.createField(fieldRequest);
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                fieldCreated,true);

    }

    @GetMapping
    public ResponseEntity<Object> getFields() {
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicFieldService.getFields(),true);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteField(@PathVariable Integer id) {
        topicFieldService.deleteField(id);
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,  null
                ,true);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getField(@PathVariable(value = "id") Integer id) {
        TopicFieldDto fieldFinded = topicFieldService.getField(id);
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK, fieldFinded,
                true);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateField(@PathVariable(value = "id") Integer id,
                                              @RequestBody TopicFieldDto fieldRequest) {
        topicFieldService.updateField(id, fieldRequest);
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,  null
                ,true);
    }
}
