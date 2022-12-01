package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageModel;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.FileService;

@RestController
@RequestMapping(value = {"/api/file" })
@CrossOrigin(origins = "http://localhost:3000")
public class FileController {
    @Autowired
    private FileService fileService;

//    @GetMapping
//    public ResponseEntity<Object> getAllFiles() {
//        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
//                fileService.getAllFiles(), true);
//    }

    @GetMapping(value = "/topic/{topicId}")
    public ResponseEntity<Object> getFilesOfTopic(@PathVariable Integer topicId) {
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                fileService.getFilesOfTopic(topicId), true);
    }

//    @PostMapping
//    public ResponseEntity<Object> saveFile(@RequestBody FileDto fileRequest) {
//       FileDto savedFile =  fileService.save(fileRequest);
//        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
//                savedFile, true);
//    }


}
