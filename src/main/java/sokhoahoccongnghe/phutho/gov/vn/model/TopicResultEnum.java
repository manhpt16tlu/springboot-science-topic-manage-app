package sokhoahoccongnghe.phutho.gov.vn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TopicResultEnum {
    DAT("Đạt"),
    KHONG_DAT("Không đạt"),
    TOT("Tốt"),
    XUAT_SAC("Xuất sắc"),
    KHONG_XAC_DINH("Không xác định");
    private final String value;
}
