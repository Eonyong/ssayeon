package a204.ssayeon.api.service;

import a204.ssayeon.db.entity.preference.Preference;
import a204.ssayeon.db.repository.preference.PreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PreferenceService {

    private final PreferenceRepository preferenceRepository;

    public void registerPreference(Preference preference) {
        preferenceRepository.save(preference);
    }

    public Preference getPreferenceById(Long preferenceId){
        Optional<Preference> option = preferenceRepository.findById(preferenceId);
        Preference preference = option.get();
        return preference;
    }
    public List<Preference> getAllPreferences(PageRequest pageRequest) {
        return preferenceRepository.findAll(pageRequest).getContent();
    }

    @Transactional
    public void deletePreference(Long preferenceId) {
        preferenceRepository.deleteById(preferenceId);
    }
}
