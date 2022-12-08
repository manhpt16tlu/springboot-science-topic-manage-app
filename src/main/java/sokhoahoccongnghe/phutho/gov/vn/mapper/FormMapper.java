package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormTypeDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Form;
import sokhoahoccongnghe.phutho.gov.vn.entity.FormType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormMapper {
    FormDto entity2Dto(Form f);
    Form dto2Entity(FormDto f);
    List<FormDto> listEntity2Dto(List<Form> l);

    FormTypeDto typeEntity2Dto(FormType f);
    FormType typeDto2Entity(FormTypeDto f);
}
