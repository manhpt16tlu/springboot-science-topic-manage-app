package sokhoahoccongnghe.phutho.gov.vn.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tap_tin_bieu_mau")
public class FormFile {
    @Id
    @Column(name = "ma", nullable = false)
    private String code;

    @Column(name = "tenbandau", nullable = false)
    private String originName;

    @Column(name = "tenmoi", nullable = false)
    private String serverName;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "mabieumau")
    private Form form;

    @Column(name = "kichthuoc")
    private long size;

    @Column(name = "ngaytao")
    private Date createDate;
}
