package a204.ssayeon.db.repository.preference;


import a204.ssayeon.db.entity.preference.PreferenceCommentsLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceCommentsLikesRepository extends JpaRepository<PreferenceCommentsLikes, Long> {

    PreferenceCommentsLikes findByPreferenceCommentsIdAndUserId(Long preferenceCommentsId, Long userId);
}