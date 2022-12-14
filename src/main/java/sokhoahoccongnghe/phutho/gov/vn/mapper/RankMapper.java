package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.RankDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Rank;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RankMapper {
    RankDto entity2Dto(Rank r);
    Rank dto2Entity(RankDto r);
    List<RankDto> listEntity2Dto(List<Rank> l);

}
