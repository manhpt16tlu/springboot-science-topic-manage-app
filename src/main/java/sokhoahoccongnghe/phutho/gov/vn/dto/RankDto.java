package sokhoahoccongnghe.phutho.gov.vn.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class RankDto {
    private Integer id;
    private String name;
    private String description;
    private Date createDate;
}
