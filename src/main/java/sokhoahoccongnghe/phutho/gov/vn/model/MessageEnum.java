package sokhoahoccongnghe.phutho.gov.vn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum MessageEnum {
    REQUEST_SUCCESS("Request Success"),
    REQUEST_FAIL("Request Fail");
    private final String value;
}
