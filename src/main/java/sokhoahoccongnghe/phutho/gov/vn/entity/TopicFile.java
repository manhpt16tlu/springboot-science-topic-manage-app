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
@Table(name = "tap_tin_de_tai",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"madetai", "maloai"})})
public class TopicFile {
    @Id
    @Column(name = "ma", nullable = false)
    private String code;

    @Column(name = "tenbandau", nullable = false)
    private String originName;

    @Column(name = "tenmoi", nullable = false)
    private String serverName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "madetai", nullable = false)
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maloai", nullable = false)
    private TopicFileType type;

    @Column(name = "kichthuoc")
    private long size;

    @Column(name = "ngaytao")
    private Date createDate;
}
