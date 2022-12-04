package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFileTypeDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicFileType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicFileTypeMapper {
    TopicFileTypeDto entity2Dto(TopicFileType t);
    TopicFileType dto2Entity(TopicFileTypeDto t);
    List<TopicFileTypeDto> listEntity2Dto(List<TopicFileType> l);
}
