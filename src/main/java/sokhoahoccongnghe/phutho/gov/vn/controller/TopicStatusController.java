package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicStatusDto;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageEnum;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicStatusService;

@RestController
@RequestMapping(value = "/api/status")
@CrossOrigin(origins = "http://localhost:3000")
public class TopicStatusController {
    @Autowired
    private TopicStatusService statusService;

    @GetMapping
    public ResponseEntity<Object> getAllStatus() {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                statusService.getAllStatus(), true);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getStatus(@PathVariable Integer id) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                statusService.getStatus(id), true);
    }

    @PostMapping
    public ResponseEntity<Object> createStatus(@RequestBody TopicStatusDto statusRequest) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                statusService.createStatus(statusRequest), true);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteStatus(@PathVariable Integer id) {
        statusService.deleteStatus(id);
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(),HttpStatus.OK, null,
                true);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateStatus(@PathVariable Integer id,@RequestBody TopicStatusDto statusRequest){
        statusService.updateStatus(id,statusRequest);
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(),HttpStatus.OK, null,
                true);
    }
}
