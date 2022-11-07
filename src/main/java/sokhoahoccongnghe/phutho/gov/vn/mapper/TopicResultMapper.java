package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicResultDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicResult;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicResultMapper {
    List<TopicResultDto> listEntity2Dto(List<TopicResult> l);
    TopicResultDto entity2Dto(TopicResult t);
    TopicResult dto2Entity(TopicResultDto t);
}
