package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.OrganDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFieldDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Organ;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicField;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapper {
//    @Mapping(source = "t.organ",target = "organDto")
    TopicDto entity2Dto(Topic t);
//    @Mapping(source = "t.organDto",target = "organ")
    Topic dto2Entity(TopicDto t);
    List<TopicDto> listEntity2Dto(List<Topic> l);
    OrganDto organ2OrganDto(Organ o); //mapstruct need to mapping topic vs topic dto
    Organ organDto2Organ(OrganDto o); //mapstruct need to mapping topic vs topic dto
    TopicField fieldDto2Entity(TopicFieldDto t); //mapstruct need to mapping topic vs topic dto
    TopicFieldDto fieldEntity2Dto(TopicField t);//mapstruct need to mapping topic vs topic dto
}
