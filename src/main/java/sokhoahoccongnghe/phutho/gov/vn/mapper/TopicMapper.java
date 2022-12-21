package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.*;
import sokhoahoccongnghe.phutho.gov.vn.entity.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    TopicDto entity2Dto(Topic t);
    Topic dto2Entity(TopicDto t);
    List<TopicDto> listEntity2Dto(List<Topic> l);
    UserDto userEntity2Dto(User o); //mapstruct need to mapping topic vs topic dto
    User userDto2Entity(UserDto o); //mapstruct need to mapping topic vs topic dto
    OrganDto userEntity2Dto(Organ o); //mapstruct need to mapping topic vs topic dto
    Organ userDto2Entity(OrganDto o);
    TopicField fieldDto2Entity(TopicFieldDto t); //mapstruct need to mapping topic vs topic dto
    TopicFieldDto fieldEntity2Dto(TopicField t);//mapstruct need to mapping topic vs topic dto
    TopicStatusDto statusEntity2Dto(TopicStatus t);
    TopicStatus statusDto2Entity(TopicStatusDto t);
    TopicResult resultDto2Entity(TopicResultDto t);
    TopicResultDto resultEntity2Dto(TopicResult t);
}
