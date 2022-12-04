package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileDto;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageEnum;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicFileService;

@RestController
@RequestMapping(value = {"/api/topicFile"})
@CrossOrigin(origins = "http://localhost:3000")
public class TopicFileController {
    @Autowired
    private TopicFileService topicFileService;
    @GetMapping
    public ResponseEntity<Object> getAll(){
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicFileService.getAllTopicFiles(), true);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody TopicFileDto topicFileDto) {
        TopicFileDto fileCreated = topicFileService.createTopicFile(topicFileDto);
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK, fileCreated,
                true);
    }

    @GetMapping(value = "/topicId/{topicId}/topicFileType/{topicFileType}")
    public ResponseEntity<Object> getTopicFilesByTopicIdAndTopicFileType(@PathVariable Integer topicId,
                                                                         @PathVariable String topicFileType){
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicFileService.getTopicFileByTopicIdAndTopicFileType(topicId,topicFileType), true);
    }
}
