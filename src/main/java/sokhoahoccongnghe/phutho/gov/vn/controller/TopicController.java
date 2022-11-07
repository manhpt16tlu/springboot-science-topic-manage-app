package sokhoahoccongnghe.phutho.gov.vn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageModel;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicService;

import java.util.List;

@RestController
@RequestMapping(value = {"/api"})
@CrossOrigin(origins = "http://localhost:3000")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping("/topic")
    public ResponseEntity<Object> getTopics() {
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.getTopics(), true);
    }

    @GetMapping(value = "/topic/{id}")
    public ResponseEntity<Object> getTopic(@PathVariable(value = "id") Integer id) {
        TopicDto topicDto = topicService.getTopic(id);
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicDto, true);
    }

    @PostMapping(value = "/organ/{organId}/field/{fieldId}/status/{statusId}/result/{resultId}/topic")
    public ResponseEntity<Object> createTopic(@PathVariable Integer organId, @PathVariable Integer fieldId,
                                              @PathVariable Integer statusId,@PathVariable Integer resultId,
                                              @RequestBody TopicDto topicRequest) {
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.createTopic(organId, fieldId, statusId,resultId,topicRequest), true);

    }

    @GetMapping(value = "/field/{fieldId}/topic")
    public ResponseEntity<Object> getTopicsByFieldId(@PathVariable Integer fieldId) {
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.getTopicsByField(fieldId), true);
    }

    @GetMapping(value = "/organ/{organId}/topic")
    public ResponseEntity<Object> getTopicsByOrganId(@PathVariable Integer organId) {
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.getTopicsByOrgan(organId), true);
    }

    @GetMapping(value = "/status/{statusId}/topic")
    public ResponseEntity<Object> getTopicsByStatusId(@PathVariable Integer statusId){
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(),HttpStatus.OK,
                topicService.getTopicsByStatus(statusId),true);
    }

    @PutMapping(value = "/topic/{id}")
    public ResponseEntity<Object> updateTopic(@PathVariable Integer id, @RequestBody TopicDto topicRequest) {
        topicService.udpateTopic(id, topicRequest);
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                null, true);
    }


    @DeleteMapping(value = "/topic/{id}")
    public ResponseEntity<Object> deleteTopic(@PathVariable Integer id) {
        topicService.deleteTopic(id);
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                null, true);
    }



}
