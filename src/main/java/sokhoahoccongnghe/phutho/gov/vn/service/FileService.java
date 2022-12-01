package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.data.jpa.repository.Query;
import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;
import sokhoahoccongnghe.phutho.gov.vn.view.FileOfTopicView;

import java.util.List;

public interface FileService {
    List<FileDto> getAllFiles();
    List<FileOfTopicView> getFilesOfTopic(Integer topicId);
    boolean existsByFileCode(String fileCode);
    FileDto getFile(String fileCode);
    FileDto save(FileDto fileDto);
}
