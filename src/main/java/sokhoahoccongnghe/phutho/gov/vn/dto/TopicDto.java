package sokhoahoccongnghe.phutho.gov.vn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class TopicDto {
    private Integer id;
    private String name;
    private String manager;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Long expense;
    private OrganDto organ;
    private TopicFieldDto topicField;
    private TopicStatusDto topicStatus;
}
