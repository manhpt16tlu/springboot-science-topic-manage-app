package sokhoahoccongnghe.phutho.gov.vn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FileTypeEnum {
    FORM("form"),
    TOPIC("topic"),
    AVATAR("avatar");
    private final String value;
}
