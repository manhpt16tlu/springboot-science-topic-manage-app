package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.data.domain.Page;
import sokhoahoccongnghe.phutho.gov.vn.dto.OrganDto;

public interface OrganService {
    OrganDto createOrgan(OrganDto o);
    Page<OrganDto> getOrgans(int page, int size,String search);
    OrganDto getOrgan(Integer id);
    void deleteOrgan(Integer id);
    void updateOrgan(Integer id,OrganDto o);
}
