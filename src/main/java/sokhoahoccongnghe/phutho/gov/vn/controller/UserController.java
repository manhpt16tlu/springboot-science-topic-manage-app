package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageEnum;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.UserService;

@RestController
@RequestMapping(value = {"/api/user"})
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{username}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                userService.getUserByUsername(username),true);
    }
    @GetMapping(value = "/existByUsername/{username}")
    public ResponseEntity<Object> existByUsername(@PathVariable String username) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                userService.checkExistByUsername(username),true);
    }
    @GetMapping(value = "/getPrincipal")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                userDetails,true);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAll(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "7") int size,
                                         @RequestParam(required = false,name = "organ") String organ,
                                         @RequestParam(required = false,name = "username") String username
                                         ) {

        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                userService.getAll( page,  size,organ,username),true);
    }
    @PatchMapping(value = "/disable/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> toggleDisableUser(@PathVariable Integer id) {
        userService.disableUser(id);
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                null,true);
    }
}
