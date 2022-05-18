package a204.ssayeon.api.service;

import a204.ssayeon.db.entity.preference.PreferenceOptionsUserSelected;
import a204.ssayeon.db.repository.preference.PreferenceOptionsUserSelectedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PreferenceOptionsUserSelectedService {

    private final PreferenceOptionsUserSelectedRepository preferenceOptionsUserSelectedRepository;

    public void registerPreferenceOptionsUserSelected(PreferenceOptionsUserSelected preferenceOptionsUserSelected) {
        preferenceOptionsUserSelectedRepository.save(preferenceOptionsUserSelected);
    }

    public PreferenceOptionsUserSelected getPreferenceOptionsUserSelectedByPreferenceIdAndUserId(Long preferenceId, Long userId) {
        return preferenceOptionsUserSelectedRepository.findByPreferenceIdAndUserId(preferenceId, userId);
    }

    public void deletePreferenceOptionsUserSelectedById(Long preferenceOptionsUserSelectedId) {
        preferenceOptionsUserSelectedRepository.deleteById(preferenceOptionsUserSelectedId);
    }

    public Long getPreferenceOptionsUserSelectedNumByPreferenceId(Long preferenceId){
        return preferenceOptionsUserSelectedRepository.countByPreferenceId(preferenceId);
    }

    public Long getPreferenceOptionsUserSelectedNumByPreferenceOptionsId(Long preferenceOptionsId){
        return preferenceOptionsUserSelectedRepository.countByPreferenceOptionsId(preferenceOptionsId);
    }
}
