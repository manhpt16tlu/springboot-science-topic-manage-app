package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.data.domain.Page;
import sokhoahoccongnghe.phutho.gov.vn.dto.OrganDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.CountOrganTopicView;

import java.util.List;

public interface OrganService {
    OrganDto createOrgan(OrganDto o);
    Page<OrganDto> getOrgans(int page, int size,String search);
    List<OrganDto> getOrgansNoPaging();
    List<CountOrganTopicView> getOrgansNeedApprove();
    OrganDto getOrgan(Integer id);
    void deleteOrgan(Integer id);
    void updateOrgan(Integer id,OrganDto o);
}
