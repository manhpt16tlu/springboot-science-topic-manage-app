package sokhoahoccongnghe.phutho.gov.vn.view;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public interface FileOfTopicView {
    String getFileCode();
    String getOriginName();
    String getServerName();
    long getSize();
//    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
    Date getCreateDate();
    String getTitle();
}
