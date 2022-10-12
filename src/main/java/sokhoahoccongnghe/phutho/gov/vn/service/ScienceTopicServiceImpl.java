package sokhoahoccongnghe.phutho.gov.vn.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.entity.ScienceTopic;
import sokhoahoccongnghe.phutho.gov.vn.repository.ScienceTopicRepository;

import java.util.List;

@Service
public class ScienceTopicServiceImpl implements ScienceTopicService{

    @Autowired
    private ScienceTopicRepository topicRepo;

    @Override
    public List<ScienceTopic> getAll() {
        List<ScienceTopic> topics = topicRepo.findAll();
        return topics;
    }
}
