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
@Table(name = "anh_dai_dien")
public class UserAvatarFile {
    @Id
    @Column(name = "ma", nullable = false)
    private String code;

    @Column(name = "ten")
    private String fileName;

    @OneToOne
    @JoinColumn(name = "manguoidung")
    private User user;

    @Column(name = "kichthuoc")
    private long size;

    @Column(name = "ngaytao")
    private Date createDate;
}
