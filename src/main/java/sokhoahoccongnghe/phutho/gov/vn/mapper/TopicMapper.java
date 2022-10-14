package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    List<TopicDto> mapListTopic2TopicDto(List<Topic> l);
    TopicDto mapTopic2TopicDto(Topic t);
}
