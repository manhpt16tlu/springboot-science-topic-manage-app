package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.FileDto;

import java.util.List;

public interface FileService {
    List<FileDto> getAllFiles();
    boolean existsByFileCode(String fileCode);
    FileDto getFile(String fileCode);
    FileDto save(FileDto fileDto);
}
