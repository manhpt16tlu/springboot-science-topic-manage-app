package sokhoahoccongnghe.phutho.gov.vn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nguoi_dung",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"tentaikhoan"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma")
    private Integer id;

    @Column(name = "ten")
    private String name;

    @Column(name = "tentaikhoan")
    private String username;

    @Column(name = "ngaytao")
    private Date createDate;

    @Column(name = "matkhau")
    @JsonIgnore
    private String password;

    @Column(name = "vohieuhoa")
    private boolean disabled;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "nguoidung_quyen",
            joinColumns = @JoinColumn(
                    name = "manguoidung"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "maquyen"
            )
    )
    private Set<Role> roles;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "macoquan", nullable = false)
    private Organ organ;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mabangcap")
    private Rank rank;
}
