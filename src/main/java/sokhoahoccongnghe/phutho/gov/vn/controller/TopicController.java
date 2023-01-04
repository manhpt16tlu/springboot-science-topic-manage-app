package sokhoahoccongnghe.phutho.gov.vn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFieldDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicStatusDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicStatus;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageEnum;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicService;
import sokhoahoccongnghe.phutho.gov.vn.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = {"/api"})
@CrossOrigin(origins = "http://localhost:3000")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/topic/nopaging")
    public ResponseEntity<Object> getTopicsNoPaging() {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.getTopicsNoPaging(), true);
    }
    @GetMapping(value = "/topic")
    public ResponseEntity<Object> getTopics(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "7") int size) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.getTopics(page, size), true);
    }

    @GetMapping(value = "/topic/approved")  //get all approved topic
    public ResponseEntity<Object> getApprovedTopics(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "7") int size) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.getApprovedTopics(page,size), true);
    }

    @GetMapping(value = "/topic/approved/filtered")  //get all approved topic with filter
    public ResponseEntity<Object> getFilteredApprovedTopics(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "7") int size,
                                                           @RequestParam(name = "name", defaultValue = "") String searchName,
                                                           @RequestParam(name = "organ") List<String> organFilter,
                                                           @RequestParam(name = "manager", defaultValue = "") String searchManganer,
                                                           @RequestParam(name = "status",defaultValue = "") String statusFilter
    ) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.getFilteredApprovedTopics(page, size, searchName, organFilter, searchManganer,statusFilter), true);
    }

    @GetMapping(value = "/organ/{organId}/topic/not_approved")  //get all non approved topic (no paging)
    public ResponseEntity<Object> getNonApprovedTopicsByOrganId(@PathVariable Integer organId) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.getNonApprovedTopicsByOrgan(organId), true);
    }

    @GetMapping(value = "/topic/{id}")
    public ResponseEntity<Object> getTopic(@PathVariable(value = "id") Integer id) {
        TopicDto topicDto = topicService.getTopic(id);
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicDto, true);
    }

    @PostMapping(value = {"/organ/{organId}/field/{fieldId}/status/{statusId}/result/{resultId}/topic",
            "/organ/{organId}/field/{fieldId}/status/{statusId}/topic"
    })
    public ResponseEntity<Object> createTopic(@PathVariable Integer organId, @PathVariable Integer fieldId,
                                              @PathVariable Integer statusId, @PathVariable(required = false) Integer resultId,
                                              @RequestBody TopicDto topicRequest) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.createTopic(organId, fieldId, statusId, resultId, topicRequest), true);

    }

    @GetMapping(value = "/field/{fieldId}/topic")
    public ResponseEntity<Object> getTopicsByFieldId(@PathVariable Integer fieldId) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.getTopicsByField(fieldId), true);
    }

//    @GetMapping(value = "/organ/{organId}/topic")
//    public ResponseEntity<Object> getTopicsByOrganId(@PathVariable Integer organId) {
//        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
//                topicService.getTopicsByOrgan(organId), true);
//    }

    @GetMapping(value = "/status/{statusId}/topic")
    public ResponseEntity<Object> getTopicsByStatusId(@PathVariable Integer statusId) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.getTopicsByStatus(statusId), true);
    }

    @GetMapping(value = "/result/{resultId}/topic")
    public ResponseEntity<Object> getTopicsByResultId(@PathVariable Integer resultId) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.getTopicsByResult(resultId), true);
    }

//    @GetMapping(value = "/uid/{uid}/topic")
//    public ResponseEntity<Object> getTopicByUID(@PathVariable String uid) {
//        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
//                topicService.getTopicByUID(uid), true);
//    }

    @PutMapping(value = "/topic/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> updateTopic(@PathVariable Integer id, @RequestBody TopicDto topicRequest) {
        topicService.updateTopic(id, topicRequest);
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                null, true);
    }

    //special update method
    @PatchMapping(value = "/approve_topic/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> approveTopic(@PathVariable Integer id) {
        topicService.approveTopic(id);
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                null, true);
    }


    @DeleteMapping(value = "/topic/{id}")
    public ResponseEntity<Object> deleteTopic(@PathVariable Integer id) {
        topicService.deleteTopic(id);
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                null, true);
    }
//    @GetMapping(value = "/topic/countByStatusId")
//    public ResponseEntity<Object> countTopicByStatusId(@RequestParam int organId, @RequestParam int statusId){
//        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
//                topicService.countTopicByStatusId(organId,statusId), true);
//    }
//    @GetMapping(value = "/topic/countByStatusName")
//    public ResponseEntity<Object> countTopicByStatusName(@RequestParam int organId, @RequestParam String statusName){
//        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
//                topicService.countTopicByStatusName(organId,statusName), true);
//    }

//    @GetMapping(value = "/topic/countByStatusAndResult")
//    public ResponseEntity<Object> countTopicByStatusAndResult(@RequestParam int organId, @RequestParam int resultId){
//        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
//                topicService.countTopicByResult(organId,resultId), true);
//    }
    @GetMapping(value = "/topic/countByName")
    public ResponseEntity<Object> countTopicByName(@RequestParam String name){
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.countTopicByName(name), true);
    }

    @GetMapping(value = "/topic/existByName")
    public ResponseEntity<Object> existByTopicName(@RequestParam String name){
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.existByName(name), true);
    }

    @PostMapping(value = {"/topic/employeeCreate"})
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<Object> employeeCreateTopic(@RequestBody TopicDto topicRequest) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.employeeCreateTopic(topicRequest), true);

    }

    @GetMapping(value = {"/topic/adminGetTopics/not_approved"})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> adminGetTopicsNeedApprove( @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "7") int size,
                                                             @RequestParam(required = false,name = "organ") String organ

    ) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.getNotApproveTopicListByAdminWithFilter(page,size,organ), true);
    }

    @GetMapping(value = {"/topic/adminGetTopics"})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> adminGetTopics( @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "7") int size,
                                                  @RequestParam(required = false,name = "name") String topicName,
                                                  @RequestParam(required = false,name = "organ") String organ,
                                                  @RequestParam(required = false,name = "manager") String managerName,
                                                  @RequestParam(required = false,name = "status") String status
    ) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.getTopicByAdminWithFilter(page,size,topicName,organ,managerName,status), true);
    }

    @GetMapping(value = "/topic/getAllByUser/{username}")
    public ResponseEntity<Object> getAllByUsername(@PathVariable String username,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "7") int size,
                                                   @RequestParam(required = false) String field,
                                                   @RequestParam(required = false,name = "name") String topicName,
                                                   @RequestParam(required = false) String status) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.getTopicByUsernameWithFilter(page,size,username,topicName,status,field), true);
    }
    @GetMapping(value = "/topic/countByManagerAndStatus")
    public ResponseEntity<Object> countByManagerAndStatus(@RequestParam(name = "status") String statusName
                                                          ) {
        UserDetails currentUser = userService.getPrincipal();
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.countTopicByManagerAndStatus(statusName,currentUser.getUsername()), true);
    }
    @GetMapping(value = "/topic/countByManagerAndResult")
    public ResponseEntity<Object> countByManagerAndResult(@RequestParam(name = "result") String resultName
    ) {
        UserDetails currentUser = userService.getPrincipal();
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.countTopicByManagerAndResult(resultName,currentUser.getUsername()), true);
    }

    @GetMapping(value = "/topic/countByStatus")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> countByStatus(@RequestParam(name = "status") String statusName
    ) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.countTopicByStatus(statusName), true);
    }
    @GetMapping(value = "/topic/countByResult")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> countByResult(@RequestParam(name = "result") String resultName
    ) {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                topicService.countTopicByResult(resultName), true);
    }
}
