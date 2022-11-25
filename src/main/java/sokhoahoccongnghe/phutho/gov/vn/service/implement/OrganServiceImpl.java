package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.OrganDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.OrganView;
import sokhoahoccongnghe.phutho.gov.vn.entity.Organ;
import sokhoahoccongnghe.phutho.gov.vn.exception.NotFoundException;
import sokhoahoccongnghe.phutho.gov.vn.mapper.OrganMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.OrganRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.OrganService;

import java.util.List;

@Service
public class OrganServiceImpl implements OrganService {
    @Autowired
    private OrganRepository organRepository;
    @Autowired
    private OrganMapper organMapper;

    @Override
    public OrganDto createOrgan(OrganDto organDto) {
        Organ organEntity = organMapper.dto2Entity(organDto);
        Organ  organCreated = organRepository.save(organEntity);
        return organMapper.entity2Dto(organCreated);
    }

    @Override
    public Page<OrganDto> getOrgans(int page,int size,String search) {
         Pageable paging = PageRequest.of(page,size);
         Page<Organ> organPageEntity;
         if(search.equals("")) organPageEntity=organRepository.findAll(paging);
         else
             organPageEntity = organRepository.findByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(search,search,paging);
         return organPageEntity.map(organMapper::entity2Dto);
    }

    @Override
    public List<OrganDto> getOrgansNoPaging() {
        List<Organ> organListEntity = organRepository.findAll();
        return organMapper.listEntity2Dto(organListEntity);
    }

    @Override
    public List<OrganView> getOrgansNeedApprove() {
        return organRepository.findByStatusWhichNeedApprove();
    }

    @Override
    public OrganDto getOrgan(Integer id) {
        Organ organFinded = getOrganEntity(id);
        return organMapper.entity2Dto(organFinded);
    }
    @Override
    public void deleteOrgan(Integer id){
        Organ organFinded = getOrganEntity(id);
        organRepository.delete(organFinded);
    }
    @Override
    public void updateOrgan(Integer id,OrganDto organRequest){
        Organ organFinded = getOrganEntity(id);
        organFinded.setName(organRequest.getName());
        organFinded.setAddress(organRequest.getAddress());
        organRepository.save(organFinded);
    }
    public Organ getOrganEntity(Integer id) {
        return organRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "organ"));
    }
}
