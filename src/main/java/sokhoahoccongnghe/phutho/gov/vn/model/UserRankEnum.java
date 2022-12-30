package sokhoahoccongnghe.phutho.gov.vn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRankEnum {
    TIEN_SI("TS"),
    THAC_SI("ThS"),
    GIAO_SU("GS"),
    PHO_GIAO_SU("PGS"),
    CU_NHAN("CN");
    private final String value;
}
