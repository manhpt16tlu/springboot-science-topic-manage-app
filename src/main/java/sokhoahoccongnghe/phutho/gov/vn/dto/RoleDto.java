package sokhoahoccongnghe.phutho.gov.vn.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sokhoahoccongnghe.phutho.gov.vn.model.RoleNameEnum;

import java.util.Date;

@Getter
@Setter
@ToString
public class RoleDto {
    private Integer id;
    private RoleNameEnum name;
    private Date createDate;
    private String description;
}
