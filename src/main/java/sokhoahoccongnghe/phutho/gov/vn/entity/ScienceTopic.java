package sokhoahoccongnghe.phutho.gov.vn.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name="de_tai")
public class ScienceTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma")
    private Integer id;

    @Column(name = "ten",nullable = false)
    private String name;

    @Column(name = "ma_linhvuc",nullable = false)
    private Integer sectorId;

    @Column(name = "ma_trangthai",nullable = false)
    private Integer statusId = 1;
}
