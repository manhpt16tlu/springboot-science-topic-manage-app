package sokhoahoccongnghe.phutho.gov.vn.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "linh_vuc")
public class TopicField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ma")
    private Integer id;
    @Column(name="tieude",nullable = false)
    private String title;
    @Column(name="mota")
    private String description;

    @Column(name="ngaytao")
    private Date createDate;
}
