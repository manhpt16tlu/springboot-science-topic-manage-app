package sokhoahoccongnghe.phutho.gov.vn.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;
import sokhoahoccongnghe.phutho.gov.vn.model.RoleNameEnum;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quyen")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ten")
    private RoleNameEnum name;

    @Column(name = "ngaytao")
    private Date createDate;

    @Column(name = "mota")
    private String description;
}
