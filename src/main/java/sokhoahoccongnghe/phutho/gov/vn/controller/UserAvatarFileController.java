package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageEnum;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.UserAvatarFileService;
import sokhoahoccongnghe.phutho.gov.vn.service.UserService;

@RestController
@RequestMapping(value = {"/api/userAvatar"})
@CrossOrigin(origins = "http://localhost:3000")
public class UserAvatarFileController {

    @Autowired
    private UserAvatarFileService avatarFileService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                avatarFileService.getAll(),true);
    }
    @GetMapping(value = "/{username}")
    public ResponseEntity<Object> getByUsername(@PathVariable String username) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                avatarFileService.getAvatarOfUser(username),true);
    }
    @GetMapping(value = "/currentUser")
    public ResponseEntity<Object> getAvatarOfCurrentUser() {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                avatarFileService.getAvatarOfCurrentUser(),true);
    }

}
