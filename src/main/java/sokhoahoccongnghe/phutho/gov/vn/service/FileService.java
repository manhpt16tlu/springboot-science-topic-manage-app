package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;

import java.util.List;

public interface FileService {
    List<FileDto> getAllFiles();
    FileDto getFile(String fileCode);
    FileDto save(FileDto fileDto);
}
