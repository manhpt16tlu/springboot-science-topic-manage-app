package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFieldDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicField;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicFieldMapper {
    TopicField dto2Entity(TopicFieldDto t);
    TopicFieldDto entity2Dto(TopicField t);
    List<TopicFieldDto> listEntity2Dto(List<TopicField> l);
}
