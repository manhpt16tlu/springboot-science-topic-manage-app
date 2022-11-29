package sokhoahoccongnghe.phutho.gov.vn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

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
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
    private Date createDate;
}
