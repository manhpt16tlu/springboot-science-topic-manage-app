package sokhoahoccongnghe.phutho.gov.vn.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ExceptionResponseModel {
    private Date time;
    private String mess;
}
