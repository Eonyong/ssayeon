package a204.ssayeon.api.service;

import a204.ssayeon.db.entity.preference.Preference;
import a204.ssayeon.db.repository.preference.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenceService {

    @Autowired
    private PreferenceRepository preferenceRepository;

    public void registerPreference(Preference preference) {
        preferenceRepository.save(preference);
    }

    public List<Preference> getAllPreferences() {
        return preferenceRepository.findAll();
    }

    public void deletePreference(Long preferenceId) {
        preferenceRepository.deleteById(preferenceId);
    }
}
