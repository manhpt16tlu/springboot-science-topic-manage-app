package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicFileType;
import sokhoahoccongnghe.phutho.gov.vn.view.TopicFileTypeView;

@Repository
public interface TopicFileTypeRepository extends JpaRepository<TopicFileType,Integer> {

    @Query(value = "select l.ma id,l.tieude title,l.ngaytao createDate,l.mota descript\n" +
            "from loai_tap_tin_de_tai l\n" +
            "where l.tieude like %:title%",nativeQuery = true)
    TopicFileTypeView findFirstByTitleContains(String title);
}
