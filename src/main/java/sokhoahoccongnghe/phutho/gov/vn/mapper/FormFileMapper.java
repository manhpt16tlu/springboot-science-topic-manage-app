package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.FormFileDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Form;
import sokhoahoccongnghe.phutho.gov.vn.entity.FormFile;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormFileMapper {
    FormFileDto entity2Dto(FormFile f);
    FormFile dto2Entity(FormFileDto f);
    List<FormFileDto> listEntity2Dto(List<FormFile> l);
    FormDto formEntity2Dto(Form f);
    Form formDto2Entity(FormDto f);
}
