package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicFile;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicFileType;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicFileMapper;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.TopicFileRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicFileService;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicService;
import sokhoahoccongnghe.phutho.gov.vn.view.TopicFileView;

import java.util.List;

@Service
public class TopicFileServiceImpl implements TopicFileService {
    @Autowired
    private TopicFileRepository topicFileRepository;
    @Autowired
    private TopicFileMapper topicFileMapper;

    @Override
    public List<TopicFileDto> getAllTopicFiles() {
        return topicFileMapper.listEntity2Dto(topicFileRepository.findAll());
    }

    @Override
    public TopicFileDto createTopicFile(TopicFileDto topicFileRequest) {
        TopicFile topicFileEntity = topicFileMapper.dto2Entity(topicFileRequest);
        return topicFileMapper.entity2Dto(topicFileRepository.save(topicFileEntity));
    }

    @Override
    public TopicFileView getTopicFileByTopicIdAndTopicFileType(Integer topicId, String topicFileType) {
        return topicFileRepository.findFileByTopicIdAndTopicFileType(topicId,topicFileType);
    }

    @Override
    public boolean checkExistByFileCode(String fileCode) {
        return topicFileRepository.existsById(fileCode);
    }
}
