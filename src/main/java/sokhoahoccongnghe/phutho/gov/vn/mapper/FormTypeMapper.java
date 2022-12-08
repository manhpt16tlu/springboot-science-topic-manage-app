package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormTypeDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.FormType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormTypeMapper {
    FormTypeDto entity2Dto(FormType f);
    FormType dto2Entity(FormTypeDto f);
    List<FormTypeDto> listEntity2Dto(List<FormType> l);
}
