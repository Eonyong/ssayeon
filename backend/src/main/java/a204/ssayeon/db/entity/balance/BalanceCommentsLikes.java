package a204.ssayeon.db.entity.balance;

import a204.ssayeon.db.entity.BaseEntity;
import a204.ssayeon.db.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BalanceCommentsLikes extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="balance_comments_likes_id")
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "balance_comments_id")
    private BalanceComments balanceComments;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}
