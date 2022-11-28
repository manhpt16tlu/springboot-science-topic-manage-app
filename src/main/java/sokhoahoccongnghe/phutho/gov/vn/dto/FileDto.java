package sokhoahoccongnghe.phutho.gov.vn.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileDto {
    private String code;
    private String originName;
    private String serverName;
    private String title;
    private TopicDto topic;
    private FileTypeDto type;
    private long size;
}
