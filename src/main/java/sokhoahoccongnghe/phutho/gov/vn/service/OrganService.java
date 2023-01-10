package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.data.domain.Page;
import sokhoahoccongnghe.phutho.gov.vn.dto.OrganDto;
import sokhoahoccongnghe.phutho.gov.vn.view.CountTopicOfOrganView;

import java.util.List;

public interface OrganService {
    OrganDto createOrgan(OrganDto o);
    Page<OrganDto> getAllWithFilter(int page, int size,String name);
    boolean existByName(String name);
    List<OrganDto> getOrgansNoPaging();
    List<CountTopicOfOrganView> getOrgansNeedApprove();
    OrganDto getOrgan(Integer id);
    void deleteOrgan(Integer id);
    void updateOrgan(Integer id,OrganDto o);
}
