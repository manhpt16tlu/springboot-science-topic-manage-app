package sokhoahoccongnghe.phutho.gov.vn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private UserDto manager;
    private Date startDate;
    private Date endDate;
    private Date createDate;
    private Long expense;
    @JsonIgnore
    private OrganDto organ;
    private TopicFieldDto topicField;
    private TopicStatusDto topicStatus;
    private TopicResultDto topicResult;
    private boolean deleted;
}
