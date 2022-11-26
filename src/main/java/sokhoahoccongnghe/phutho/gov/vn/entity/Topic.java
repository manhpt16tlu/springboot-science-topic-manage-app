package sokhoahoccongnghe.phutho.gov.vn.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "de_tai")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma")
    private Integer id;

    @Column(name="uid",nullable = false)
    private String uid;

    @Column(name = "ten", nullable = false)
    private String name;

    @Column(name = "chunhiem", nullable = false)
    private String manager;

    @Column(name = "ngaybatdau",nullable = false)
//    @JsonFormat(pattern = "yyyy-MM-dd") //timezone set in resources/application.properties
    private Date startDate;

    @Column(name = "ngayketthuc",nullable = false)
//    @JsonFormat(pattern = "yyyy-MM-dd") //timezone set in resources/application.properties
    private Date endDate;

    @Column(name = "ngaytao",nullable = false)
    private Date createDate;


    @Column(name = "kinhphi")
    private Long expense;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "macoquan", nullable = false)
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
}
