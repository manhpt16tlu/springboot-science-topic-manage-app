package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicStatusDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicStatus;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicStatusMapper {
    TopicStatusDto entity2Dto(TopicStatus t);
    TopicStatus dto2Entity(TopicStatusDto t);
    List<TopicStatusDto> listEntity2Dto(List<TopicStatus> l);
}
