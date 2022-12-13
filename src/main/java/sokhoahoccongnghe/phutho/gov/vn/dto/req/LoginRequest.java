package sokhoahoccongnghe.phutho.gov.vn.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class LoginRequest {
    @NotBlank
    @Size(min = 10,max = 30)
    private String username;

    @NotBlank
    @Size(min = 8,max = 30)
    private String password;
}
