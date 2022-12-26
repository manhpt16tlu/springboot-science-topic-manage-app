package sokhoahoccongnghe.phutho.gov.vn.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class TopicStatusDto {
    private Integer id;
    private String title;
    private String description;

    private Date createDate;
}
