package sokhoahoccongnghe.phutho.gov.vn.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ket_qua")
public class TopicResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma")
    private Integer id;

    @Column(name = "tieude")
    private String title;

    @Column(name = "mota")
    private String description;

    @Column(name="ngaytao")
    private Date createDate;
}
