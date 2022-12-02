package sokhoahoccongnghe.phutho.gov.vn.view;

import java.util.Date;

public interface FormFileView {
    String getFileCode();
    long getSize();
    Date getCreateDate();
    String getOriginName();
    String getServerName();
    String getTitle();
    String getfileType();
}
