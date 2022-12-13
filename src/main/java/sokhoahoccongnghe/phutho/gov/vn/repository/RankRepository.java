package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.Rank;

@Repository
public interface RankRepository extends JpaRepository<Rank,Integer> {
    Rank findByName(String name);
}
