package a204.ssayeon.db.entity.balance;

import a204.ssayeon.db.entity.BaseEntity;
import a204.ssayeon.db.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BalanceComments extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="balance_comments_id")
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "balance_id")
    private Balance balance;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private String description;
}