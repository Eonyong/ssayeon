package a204.ssayeon.api.service;

import a204.ssayeon.db.entity.preference.PreferenceComments;
import a204.ssayeon.db.repository.preference.PreferenceCommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PreferenceCommentsService {

    private final PreferenceCommentsRepository preferenceCommentsRepository;

    public void registerPreferenceComments(PreferenceComments preferenceComments) {
        preferenceCommentsRepository.save(preferenceComments);
    }

    public List<PreferenceComments> getAllPreferenceCommentsByPreferenceId(Long preferenceId) {
        List<PreferenceComments> list = preferenceCommentsRepository.findByPreferenceId(preferenceId);
        return list;
    }

    public PreferenceComments getPreferenceCommentsById(Long commentsId) {
        return preferenceCommentsRepository.findById(commentsId).get();
    }

    public void deletePreferenceCommentsById(Long commentsId) {
        preferenceCommentsRepository.deleteById(commentsId);
    }
}
