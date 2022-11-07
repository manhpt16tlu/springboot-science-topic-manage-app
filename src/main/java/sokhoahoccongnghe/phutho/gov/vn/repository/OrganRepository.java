package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.Organ;

import java.util.List;

@Repository
public interface OrganRepository extends JpaRepository<Organ,Integer> {
    Page<Organ> findByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String name, String add, Pageable p);
}
