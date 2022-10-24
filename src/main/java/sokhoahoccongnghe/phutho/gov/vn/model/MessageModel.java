package sokhoahoccongnghe.phutho.gov.vn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum MessageModel {
    REQUEST_SUCCESS("request success"),
    REQUEST_FAIL("request fail");
    private final String value;
}
