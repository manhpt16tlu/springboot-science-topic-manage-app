package sokhoahoccongnghe.phutho.gov.vn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sokhoahoccongnghe.phutho.gov.vn.entity.ScienceTopic;
import sokhoahoccongnghe.phutho.gov.vn.service.ScienceTopicService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/topic")
public class ScienceTopicController {
    @Autowired
    private ScienceTopicService topicService;

    @GetMapping
    public List<ScienceTopic> getAllTopic() {
        List<ScienceTopic> topics = topicService.getAll();
        return topics;
    }


}
