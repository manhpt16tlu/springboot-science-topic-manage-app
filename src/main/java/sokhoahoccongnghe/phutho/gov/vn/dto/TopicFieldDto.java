package sokhoahoccongnghe.phutho.gov.vn.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class TopicFieldDto {
    private Integer id;
    private String title;
    private String description;

    private Date createDate;
}
