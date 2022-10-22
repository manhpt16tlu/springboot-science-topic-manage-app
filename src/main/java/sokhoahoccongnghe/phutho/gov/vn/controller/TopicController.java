package sokhoahoccongnghe.phutho.gov.vn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicService;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/topic", "/api"})
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TopicDto> getTopic(@PathVariable(value = "id") Integer id) {
        TopicDto o = topicService.getTopic(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @PostMapping(value = "/organ/{organId}/field/{fieldId}/topic")
    public ResponseEntity<TopicDto> createTopic(@PathVariable Integer organId, @PathVariable Integer fieldId, @RequestBody TopicDto topicRequest) {
        return new ResponseEntity<>(topicService.createTopic(organId, fieldId, topicRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/field/{fieldId}/topic")
    public ResponseEntity<List<TopicDto>> getTopicsByFieldId(@PathVariable Integer fieldId){
        return new ResponseEntity<>(topicService.getTopicsByField(fieldId),HttpStatus.OK);
    }

    @GetMapping(value = "/organ/{organId}/topic")
    public ResponseEntity<List<TopicDto>> getTopicsByOrganId(@PathVariable Integer organId) {
        return new ResponseEntity<>(topicService.getTopicsByOrgan(organId), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateTopic(@PathVariable Integer id, @RequestBody TopicDto topicRequest) {
        topicService.udpateTopic(id, topicRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTopic(@PathVariable Integer id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

}
