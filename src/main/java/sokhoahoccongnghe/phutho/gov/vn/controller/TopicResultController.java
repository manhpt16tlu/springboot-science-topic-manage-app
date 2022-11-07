package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicResultDto;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageModel;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicResultService;

@RestController
@RequestMapping(value="/api/result")
@CrossOrigin(origins = "http://localhost:3000")
public class TopicResultController {
    @Autowired
    private TopicResultService resultService;

    @GetMapping()
    public ResponseEntity<Object> getAllResult(){
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                resultService.getAllResult(), true);
    }

    @PostMapping
    public ResponseEntity<Object> createResult(@RequestBody TopicResultDto resultRequest){
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                resultService.createResult(resultRequest), true);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getResult(@PathVariable Integer id){
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                resultService.getResult(id), true);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteResult(@PathVariable Integer id){
        resultService.deleteResult(id);
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(),HttpStatus.OK, null,
                true);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateResult(@PathVariable Integer id,@RequestBody TopicResultDto resultRequest){
        resultService.updateResult(id,resultRequest);
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(),HttpStatus.OK, null,
                true);
    }
}
