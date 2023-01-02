package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageEnum;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.service.RankService;

@RestController
@RequestMapping(value = {"/api/userRank"})
@CrossOrigin(origins = "http://localhost:3000")
public class UserRankController {
    @Autowired
    private RankService rankService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                rankService.getAll(),true);
    }
}
