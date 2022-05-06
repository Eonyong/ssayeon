package a204.ssayeon.api.service;

import a204.ssayeon.db.entity.preference.Preference;
import a204.ssayeon.db.repository.preference.PreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Preference> getAllPreferences(Pageable pageable) {
        return preferenceRepository.findAll(pageable);
    }

    @Transactional
    public void deletePreference(Long preferenceId) {
        preferenceRepository.deleteById(preferenceId);
    }

    public Page<Preference> searchPreferences(Integer type, String query) {
        if (type == 1) { // 질문
            preferenceRepository.findByDescriptionContaining(query);
        } else if (type == 2) { // 작성자
//            preferenceRepository.findByDescriptionContaining(query);
        } else if (type == 3) { // 선택지
//            preferenceRepository.findByDescriptionContaining(query);
        }
        return null;
    }
}
