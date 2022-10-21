package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.OrganDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Organ;

import java.util.List;

public interface OrganService {
    OrganDto createOrgan(OrganDto o);
    List<OrganDto> getOrgans();
    OrganDto getOrgan(Integer id);
    void deleteOrgan(Integer id);
    void updateOrgan(Integer id,OrganDto o);
}
