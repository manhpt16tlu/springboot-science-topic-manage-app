package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.ScienceTopic;

@Repository
public interface ScienceTopicRepository extends JpaRepository<ScienceTopic,Integer> {
}
