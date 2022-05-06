package a204.ssayeon.db.entity.balance;

import a204.ssayeon.db.entity.BaseEntity;
import a204.ssayeon.db.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BalanceComments extends BaseEntity {

    @Id @GeneratedValue
    @Column(name="balance_comments_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "balance_id")
    private Balance balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(nullable = false)
    private String description;

    public void updateDescription(String description){
        this.description = description;
    }

}
