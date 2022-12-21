package sokhoahoccongnghe.phutho.gov.vn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TopicFileTypeEnum {
    DE_CUONG("Đề cương"),
    BAO_CAO("Báo cáo");
    private final String value;
}
