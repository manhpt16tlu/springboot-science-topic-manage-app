package sokhoahoccongnghe.phutho.gov.vn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@ToString
public class OrganDto {
    private Integer id;
    private String name;
    private String address;
    private String email;
    private Date createDate;
}
