package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicFile;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicFileType;
import sokhoahoccongnghe.phutho.gov.vn.view.TopicFileView;

@Repository
public interface TopicFileRepository extends JpaRepository<TopicFile,String> {

    @Query(value = "select t.ma fileCode,t.tenbandau originName,t.tenmoi serverName,t.kichthuoc size from tap_tin_de_tai t\n" +
            "where t.madetai = :topicId and t.maloai \n" +
            "in (select l.ma from loai_tap_tin_de_tai l where l.tieude like %:topicFileType%)",nativeQuery = true)
    TopicFileView findFileByTopicIdAndTopicFileType(Integer topicId,String topicFileType);


}
