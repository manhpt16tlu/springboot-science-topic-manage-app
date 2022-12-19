package sokhoahoccongnghe.phutho.gov.vn.dto.res;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenResponse {
    private String accessToken;
    private String username;
    private List<String> roles;
    private String type = "Bearer";
    private Date expirationTime;
}
