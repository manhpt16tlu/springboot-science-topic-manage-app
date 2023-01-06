package sokhoahoccongnghe.phutho.gov.vn.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@SQLDelete(sql = "update de_tai set xoa = true WHERE ma=?") //override delete
@Where(clause = "xoa = false") // filter bản ghi
@Table(name = "de_tai")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma")
    private Integer id;

    @Column(name = "ten", nullable = false)
    private String name;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manguoidung", nullable = false)
    private User manager;

    @Column(name = "ngaybatdau",nullable = false)
    private Date startDate;

    @Column(name = "ngayketthuc",nullable = false)
    private Date endDate;

    @Column(name = "ngaytao",nullable = false)
    private Date createDate;


    @Column(name = "kinhphi")
    private Long expense;

    @Transient
    private Organ organ;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "malinhvuc", nullable = false)
    private TopicField topicField;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "matrangthai", nullable = false)
    private TopicStatus topicStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maketqua", nullable = false)
    private TopicResult topicResult;

    @Column(name = "xoa")
    private boolean deleted = false;
}
