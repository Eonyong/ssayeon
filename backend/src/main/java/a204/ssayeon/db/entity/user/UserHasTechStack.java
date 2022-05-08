package a204.ssayeon.db.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserHasTechStack {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_has_tech_stack_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "tech_stack_id")
    private TechStack techStack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}
