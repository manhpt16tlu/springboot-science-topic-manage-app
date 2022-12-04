package sokhoahoccongnghe.phutho.gov.vn.view;

import java.util.Date;

public interface TopicFileView {
    String getFileCode();
    long getSize();
    Date getCreateDate();
    String getOriginName();
    String getServerName();
}
