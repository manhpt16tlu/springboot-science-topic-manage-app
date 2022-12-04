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
@Table(name = "loai_tap_tin_de_tai")
public class TopicFileType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma")
    private Integer id;
    @Column(name = "tieude")
    private String title;
    @Column(name = "ngaytao")
    private Date createDate;
    @Column(name = "mota")
    private String description;
}
