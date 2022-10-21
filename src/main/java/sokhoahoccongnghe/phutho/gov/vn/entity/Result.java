package sokhoahoccongnghe.phutho.gov.vn.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "ket_qua")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma")
    private Integer id;

    @Column(name = "tieude", nullable = false)
    private String title;

    @Column(name = "mota")
    private String descript;
}
