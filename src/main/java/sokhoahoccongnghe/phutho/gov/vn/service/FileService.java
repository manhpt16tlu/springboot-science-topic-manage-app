package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;
import sokhoahoccongnghe.phutho.gov.vn.view.FileOfTopicView;
import sokhoahoccongnghe.phutho.gov.vn.view.FormFileView;

import java.util.List;

public interface FileService {
    List<FileDto> getAllFiles();
    List<FileOfTopicView> getFilesOfTopic(Integer topicId);
    List<FormFileView> getFilesByTypeName(String typeName);
    boolean existsByFileCode(String fileCode);
    FileDto getFile(String fileCode);
    FileDto save(FileDto fileDto);
}
