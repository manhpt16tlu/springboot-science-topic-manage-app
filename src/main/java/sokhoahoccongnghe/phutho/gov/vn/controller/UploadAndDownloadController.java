package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageModel;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.UploadAndDownloadService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UploadAndDownloadController {

    @Autowired
    private UploadAndDownloadService updownService;

    @PostMapping(value = "/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam(name = "file") MultipartFile fileUpload,
                                             @RequestParam(name = "type") String fileType,
                                             @RequestParam(name = "title",required = false) String fileTitle,
                                             @RequestParam(name = "topicId",required = false) Integer topicId) {
        FileDto savedFile = updownService.upload(fileUpload, fileType,fileTitle,topicId);
        return ResponseBaseModel.responseBuidler(MessageModel.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                savedFile, true);
    }

    @GetMapping(value = "/download/{fileCode}")
    public ResponseEntity<Object> downloadFile(@PathVariable String fileCode){
        Resource fileResource = updownService.getFile(fileCode);
        HttpHeaders headers = new HttpHeaders();
        //có hai cách tạo http headers
//        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
//        headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=x.docx");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ContentDisposition contentDisposition = ContentDisposition.attachment()
                .filename(fileResource.getFilename())
                .build();
        headers.setContentDisposition(contentDisposition);
        return new ResponseEntity<>(fileResource,headers,HttpStatus.OK);
    }
}
