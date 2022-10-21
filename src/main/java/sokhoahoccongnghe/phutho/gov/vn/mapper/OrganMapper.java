package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.OrganDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Organ;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrganMapper {
    OrganDto entity2Dto(Organ o);
    Organ dto2Entity(OrganDto o);
    List<OrganDto> listEntity2Dto(List<Organ> o);
}
