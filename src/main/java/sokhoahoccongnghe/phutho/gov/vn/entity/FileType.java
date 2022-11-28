package sokhoahoccongnghe.phutho.gov.vn.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loai_tap_tin")
public class FileType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ma")
    private Integer id;
    @Column(name="ten",nullable = false)
    private String name;
    @Column(name="mota")
    private String description;
}
