package a204.ssayeon.db.entity.user;

import lombok.*;

import javax.persistence.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class UserCertification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_certification_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String classId;
}
