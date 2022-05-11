package a204.ssayeon.db.repository.preference;


import a204.ssayeon.db.entity.preference.PreferenceOptionsUserSelected;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceOptionsUserSelectedRepository extends JpaRepository<PreferenceOptionsUserSelected, Long> {

    PreferenceOptionsUserSelected findByPreferenceIdAndUserId(Long preferenceId, Long userId);
}