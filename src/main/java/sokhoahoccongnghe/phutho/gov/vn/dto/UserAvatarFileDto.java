package sokhoahoccongnghe.phutho.gov.vn.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sokhoahoccongnghe.phutho.gov.vn.entity.User;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserAvatarFileDto {
    private String code;
    private String fileName;
    private UserDto user;
    private long size;
    private Date createDate;
}
