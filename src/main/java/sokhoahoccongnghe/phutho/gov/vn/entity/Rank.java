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
@Table(name = "bang_cap")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma")
    private Integer id;

    @Column(name = "ten")
    private String name;


    @Column(name = "mota")
    private String description;

    @Column(name = "ngaytao")
    private Date createDate;
}
