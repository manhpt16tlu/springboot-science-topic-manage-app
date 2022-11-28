package sokhoahoccongnghe.phutho.gov.vn.mapper;

import org.mapstruct.Mapper;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileTypeDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.File;
import sokhoahoccongnghe.phutho.gov.vn.entity.FileType;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper {
    FileDto entity2Dto(File f);
    File dto2Entity(FileDto f);
    List<FileDto> listEntity2Dto(List<File> l);
    FileTypeDto typeEntity2Dto(FileType ft);
    FileType typeDto2Entity(FileTypeDto ft);
    TopicDto topicEntity2Dto(Topic t);
    Topic topicDto2Entity(TopicDto t);

}
