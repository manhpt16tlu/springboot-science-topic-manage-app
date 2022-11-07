package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.*;
import sokhoahoccongnghe.phutho.gov.vn.entity.*;

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
    TopicStatusDto statusEntity2Dto(TopicStatus t);
    TopicStatus statusDto2Entity(TopicStatusDto t);
    TopicResult resultDto2Entity(TopicResultDto t);
    TopicResultDto resultEntity2Dto(TopicResult t);
}
