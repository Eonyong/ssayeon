package a204.ssayeon.api.service;

import a204.ssayeon.db.entity.preference.PreferenceOptions;
import a204.ssayeon.db.repository.preference.PreferenceOptionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PreferenceOptionsService {

    private final PreferenceOptionsRepository preferenceOptionsRepository;

    public void registerPreferenceOptions(PreferenceOptions preferenceOptions) {
        preferenceOptionsRepository.save(preferenceOptions);
    }

    public List<PreferenceOptions> getPreferenceOptionsByPreferenceId(Long preferenceId) {
        List<PreferenceOptions> list = preferenceOptionsRepository.findByPreferenceId(preferenceId);
        return list;
    }

    public PreferenceOptions getPreferenceOptionsById(Long preferenceOptionsId) {
        return preferenceOptionsRepository.findById(preferenceOptionsId).get();
    }

    @Transactional
    public void deletePreferenceOptionsByPreferenceId(Long preferenceId) {
        preferenceOptionsRepository.deleteByPreferenceId(preferenceId);
    }
}
