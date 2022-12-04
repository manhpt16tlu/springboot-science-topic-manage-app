package sokhoahoccongnghe.phutho.gov.vn.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class TopicFileTypeDto {
    private Integer id;
    private String title;
    private Date createDate;
    private String description;
}
