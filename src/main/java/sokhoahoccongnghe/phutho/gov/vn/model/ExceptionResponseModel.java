package sokhoahoccongnghe.phutho.gov.vn.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ExceptionResponseModel {
    @JsonFormat(pattern = "E,dd MMM yyyy hh:mm:ss")
    private Date time;
    private String message;
}
