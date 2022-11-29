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
@Table(name = "tap_tin")
public class File {
    @Id
    @Column(name="ma",nullable = false)
    private String code;

    @Column(name="tenbandau",nullable = false)
    private String originName;

    @Column(name="tenmoi",nullable = false)
    private String serverName;

    @Column(name="tieude")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "madetai")
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maloai", nullable = false)
    private FileType type;

    @Column(name = "kichthuoc")
    private long size;

    @Column(name = "ngaytao")
    private Date createDate;
}
