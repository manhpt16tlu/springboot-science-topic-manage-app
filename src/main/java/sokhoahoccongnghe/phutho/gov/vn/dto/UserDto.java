package sokhoahoccongnghe.phutho.gov.vn.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
public class UserDto {
    private Integer id;
    private String name;
    private String username;
    private Date createDate;
    private String password;
    private Set<RoleDto> roles;
    private OrganDto organ;
    private RankDto rank;
}
