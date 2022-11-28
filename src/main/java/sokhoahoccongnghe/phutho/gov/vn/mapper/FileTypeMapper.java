package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileTypeDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.FileType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileTypeMapper {
    FileTypeDto entity2Dto(FileType f);
    FileType dto2Entity(FileTypeDto f);
    List<FileTypeDto> listEntity2Dto(List<FileType> l);

}
