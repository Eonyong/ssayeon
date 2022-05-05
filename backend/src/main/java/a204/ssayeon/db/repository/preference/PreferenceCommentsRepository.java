package a204.ssayeon.db.repository.preference;


import a204.ssayeon.db.entity.preference.PreferenceComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreferenceCommentsRepository extends JpaRepository<PreferenceComments, Long> {

    List<PreferenceComments> findByPreferenceId(Long preferenceId);
}