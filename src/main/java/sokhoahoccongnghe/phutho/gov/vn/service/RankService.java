package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.RankDto;

import java.util.List;

public interface RankService {
    RankDto getRankByName(String name);
    List<RankDto> getAll();
}
