package a204.ssayeon.db.repository.preference;


import a204.ssayeon.db.entity.preference.PreferenceOptions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreferenceOptionsRepository extends JpaRepository<PreferenceOptions, Long> {

    List<PreferenceOptions> findByPreferenceId(Long preferenceId);

    void deleteByPreferenceId(Long preferenceId);
}