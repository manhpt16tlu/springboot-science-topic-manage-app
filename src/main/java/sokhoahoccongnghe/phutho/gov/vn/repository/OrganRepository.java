package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.dto.CountOrganTopicView;
import sokhoahoccongnghe.phutho.gov.vn.entity.Organ;

import java.util.List;

@Repository
public interface OrganRepository extends JpaRepository<Organ,Integer> {
    Page<Organ> findByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String name, String add, Pageable p);
    @Query(value = "select organ.ma id,organ.ten name,count(topic.ma) numberOfTopic\n" +
            "from co_quan_chu_tri organ\n" +
            "inner join de_tai topic\n" +
            "on topic.macoquan = organ.ma\n" +
            "where topic.matrangthai in (select status.ma from trang_thai status where status.tieude = 'Chưa duyệt')" +
            "\n" +
            "group by organ.ma;",nativeQuery = true)
    List<CountOrganTopicView> findByStatusWhichNeedApprove();
}
