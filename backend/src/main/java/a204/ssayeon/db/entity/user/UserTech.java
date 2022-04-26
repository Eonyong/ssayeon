package a204.ssayeon.db.entity.user;

import a204.ssayeon.db.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserTech extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_has_tech_id")
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "tech_stack_id")
    private TechStack techStack;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}
