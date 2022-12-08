package sokhoahoccongnghe.phutho.gov.vn.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class FormFileDto {
    private String code;
    private String originName;
    private String serverName;
    private FormDto form;
    private long size;
    private Date createDate;
}
