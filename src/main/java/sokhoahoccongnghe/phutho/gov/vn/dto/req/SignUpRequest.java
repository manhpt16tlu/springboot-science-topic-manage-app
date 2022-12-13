package sokhoahoccongnghe.phutho.gov.vn.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sokhoahoccongnghe.phutho.gov.vn.dto.OrganDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.RankDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank
    @Size(min = 10,max = 30)
    private String username;

    @NotBlank
    @Size(min = 8,max = 30)
    private String password;

    @NotBlank
    @Size(min = 5)
    private String name;

    @NotNull
    private OrganDto organ;

    @NotEmpty
    private Set<String> roles;

    @NotNull
    private RankDto rank;
}
