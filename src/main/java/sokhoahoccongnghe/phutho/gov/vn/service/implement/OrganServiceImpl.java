package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.OrganDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.Organ;
import sokhoahoccongnghe.phutho.gov.vn.exception.NotFoundException;
import sokhoahoccongnghe.phutho.gov.vn.mapper.OrganMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.OrganRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.OrganService;
import sokhoahoccongnghe.phutho.gov.vn.view.CountTopicOfOrganView;

import java.util.Date;
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
        organEntity.setCreateDate(new Date());
        Organ organCreated = organRepository.save(organEntity);

        return organMapper.entity2Dto(organCreated);
    }

    @Override
    public Page<OrganDto> getAllWithFilter(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");//tạo sau thì hiển thị trước
        Pageable paging = PageRequest.of(page, size, sort);
        Page<Organ> organPageEntity = organRepository.findAllWithFilter(paging);
        return organPageEntity.map(organMapper::entity2Dto);
    }

    @Override
    public boolean existByName(String name) {
         return organRepository.existsByName(name);
    }

    @Override
    public List<OrganDto> getOrgansNoPaging() {
        List<Organ> organListEntity = organRepository.findAll();
        return organMapper.listEntity2Dto(organListEntity);
    }

    @Override
    public List<CountTopicOfOrganView> getOrgansNeedApprove() {
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
        organFinded.setEmail(organRequest.getEmail());
        organFinded.setAddress(organRequest.getAddress());
        organRepository.save(organFinded);
    }
    public Organ getOrganEntity(Integer id) {
        return organRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "organ"));
    }
}
