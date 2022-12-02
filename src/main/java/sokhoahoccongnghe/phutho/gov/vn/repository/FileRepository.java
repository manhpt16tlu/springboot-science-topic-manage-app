package sokhoahoccongnghe.phutho.gov.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sokhoahoccongnghe.phutho.gov.vn.entity.File;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;
import sokhoahoccongnghe.phutho.gov.vn.view.FileOfTopicView;
import sokhoahoccongnghe.phutho.gov.vn.view.FormFileView;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,String> {
    List<File> findByTopic(Topic t);

    @Query(value = "select t.ma fileCode,t.tenbandau originName,t.tenmoi serverName,t.kichthuoc size,\n" +
            "t.ngaytao createDate, t.tieude title\n" +
            "from tap_tin t where t.madetai = ?1",nativeQuery = true)
    List<FileOfTopicView> findByTopic(Integer topicId);

    @Query(value = "select t.ma fileCode,t.kichthuoc size,t.ngaytao createDate,t.tenbandau originName,\n" +
            "t.tenmoi serverName,t.tieude title,l.ten fileType\n" +
            "from tap_tin t inner join loai_tap_tin l on t.maloai = l.ma\n" +
            "where lower(l.ten) = ?1",nativeQuery = true)
    List<FormFileView> findByFileType(String typeName);

}
