package a204.ssayeon.db.entity.preference;

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
public class PreferenceCommentsLikes{

    @Id @GeneratedValue
    @Column(name="preference_comment_likes_id")
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "preference_comments_id")
    private PreferenceComments preferenceComments;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}
