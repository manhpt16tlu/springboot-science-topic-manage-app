package sokhoahoccongnghe.phutho.gov.vn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum MessageEnum {
    REQUEST_SUCCESS("Request Success"),
    REQUEST_FAIL("Request Fail"),
    USER_DISABLED("User is disabled"),
    USER_UNAUTHORIZED("Can not authenticate user");

    private final String value;
}
