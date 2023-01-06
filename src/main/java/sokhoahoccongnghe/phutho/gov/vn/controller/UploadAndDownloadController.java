package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileDto;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageEnum;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.UploadAndDownloadService;
import sokhoahoccongnghe.phutho.gov.vn.service.UserService;

@RestController
@RequestMapping(value = "/api")
//allow exposed content disposition in client
@CrossOrigin(origins = {"http://localhost:3000"},exposedHeaders = {"Content-Disposition"})
public class UploadAndDownloadController {

    @Autowired
    private UploadAndDownloadService updownService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/upload/topic")
    public ResponseEntity<Object> uploadTopicFile(@RequestParam(name = "fileUpload") MultipartFile fileUpload,
                                             @RequestParam(name = "topicFileType") String topicFileType,
                                             @RequestParam(name = "topic") Integer topicId) {
        TopicFileDto uploadedFile = updownService.uploadTopicFile(fileUpload,topicFileType,topicId);
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                uploadedFile, true);
    }
    @PostMapping(value = "/upload/form")
    public ResponseEntity<Object> uploadFormFile(@RequestParam(name = "fileUpload") MultipartFile fileUpload,
                                                 @RequestParam(name = "formId") Integer formId) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                updownService.uploadFormFile(fileUpload,formId), true);
    }



    @GetMapping(value = "/download/{fileType}/{fileCode}")
    public ResponseEntity<Object> downloadFile(@PathVariable String fileCode,@PathVariable String fileType){
        Resource fileResource = updownService.getFile(fileType,fileCode);
        HttpHeaders headers = new HttpHeaders();
        //có hai cách tạo http headers
//        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
//        headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=x.docx");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // refactor later
        ContentDisposition contentDisposition = ContentDisposition.attachment()
                .filename(fileResource.getFilename())
                .build();
        headers.setContentDisposition(contentDisposition);
        return new ResponseEntity<>(fileResource,headers,HttpStatus.OK);
    }

    @PostMapping(value = "/upload/avatar")
    public ResponseEntity<Object> uploadUserAvatarFile(@RequestParam(name = "fileUpload") MultipartFile fileUpload) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                updownService.uploadAvatarFile(fileUpload), true);
    }
    @DeleteMapping(value = "/delete/avatar")
    public ResponseEntity<Object> deleteAvatarOfCurrentUser() {
        updownService.deleteAvatar(userService.getPrincipal().getUsername());
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                null,true);
    }
    @DeleteMapping(value = "/delete/form/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> deleteForm(@PathVariable Integer id) {
        updownService.deleteForm(id);
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                null, true);
    }
}
