package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileTypeDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicFile;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicFileType;

import java.util.List;
@Mapper(componentModel = "spring")
public interface TopicFileMapper {
    TopicFileDto entity2Dto(TopicFile t);
    TopicFile dto2Entity(TopicFileDto t);
    List<TopicFileDto> listEntity2Dto(List<TopicFile> l);
    TopicFileTypeDto typeEntity2Dto(TopicFileType t);
    TopicFileType typeDto2Entity(TopicFileTypeDto t);
    TopicDto topicEntity2Dto(Topic t);
    Topic topicDto2Entity(TopicDto t);
}
