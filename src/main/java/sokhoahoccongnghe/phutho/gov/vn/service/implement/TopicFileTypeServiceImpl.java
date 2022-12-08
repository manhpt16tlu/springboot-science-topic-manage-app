package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileTypeDto;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicFileTypeMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.TopicFileTypeRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicFileTypeService;
import sokhoahoccongnghe.phutho.gov.vn.view.TopicFileTypeView;

@Service
public class TopicFileTypeServiceImpl implements TopicFileTypeService {
    @Autowired
    private TopicFileTypeRepository topicFileTypeRepository;
    @Autowired
    private TopicFileTypeMapper topicFileTypeMapper;
    @Override
    public TopicFileTypeDto getTopicFileTypeById(Integer topicFileTypeId) {
        return topicFileTypeMapper.entity2Dto(topicFileTypeRepository.getById(topicFileTypeId));
    }

    @Override
    public TopicFileTypeView getTopicFileTypeViewByTitle(String topicFileTypeTitle) {
       return topicFileTypeRepository.findFirstByTitleContains(topicFileTypeTitle);

    }
}
