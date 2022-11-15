package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageModel;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicService;

import java.util.HashMap;

@RestController
@RequestMapping(value = {"/api/count"})
@CrossOrigin(origins = "http://localhost:3000")
public class CountController {

    @Autowired
    private TopicService topicService;

    @GetMapping(value = "/topic/by/status")
    public ResponseEntity<Object> countTopicByStatus(@RequestParam int organId, @RequestParam int statusId){
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.countTopicByStatus(organId,statusId), true);
    }

    @GetMapping(value = "/topic/by/result")
    public ResponseEntity<Object> countTopicByResult(@RequestParam int organId, @RequestParam int resultId){
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.countTopicByResult(organId,resultId), true);
    }
    @GetMapping(value = "/topic/by/name")
    public ResponseEntity<Object> countTopicByName(@RequestParam String name){
//        System.out.println(name);
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.countTopicByName(name), true);
    }
}
