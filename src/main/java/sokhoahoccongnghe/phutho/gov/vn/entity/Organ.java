package sokhoahoccongnghe.phutho.gov.vn.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "co_quan_chu_tri")
public class Organ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma")
    private Integer id;

    @Column(name = "ten", nullable = false)
    private String name;

    @Column(name = "diachi")
    private String address;

    @Column(name="email")
    private String email;

    @Column(name="ngaytao")
    private Date createDate;
}
