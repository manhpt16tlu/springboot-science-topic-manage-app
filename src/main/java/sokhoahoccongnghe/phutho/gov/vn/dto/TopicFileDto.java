package sokhoahoccongnghe.phutho.gov.vn.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sokhoahoccongnghe.phutho.gov.vn.entity.Topic;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicFileType;

import java.util.Date;

@Getter
@Setter
@ToString
public class TopicFileDto {
    private String code;
    private String originName;
    private String serverName;
    private TopicDto topic;
    private TopicFileTypeDto type;
    private long size;
    private Date createDate;
}
