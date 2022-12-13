package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.RankDto;
import sokhoahoccongnghe.phutho.gov.vn.mapper.RankMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.RankRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.RankService;

@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankRepository rankRepository;
    @Autowired
    private RankMapper rankMapper;
    @Override
    public RankDto getRankByName(String name) {
        return rankMapper.entity2Dto(rankRepository.findByName(name));
    }
}
