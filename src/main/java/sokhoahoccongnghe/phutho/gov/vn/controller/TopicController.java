package sokhoahoccongnghe.phutho.gov.vn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping
    public List<TopicDto> getAllTopic() {
        List<TopicDto> topics = topicService.getAll();
        return topics;
    }


}
