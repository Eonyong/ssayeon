package a204.ssayeon.api.service;

import a204.ssayeon.db.entity.preference.PreferenceCommentsLikes;
import a204.ssayeon.db.repository.preference.PreferenceCommentsLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PreferenceCommentsLikesService {

    private final PreferenceCommentsLikesRepository preferenceCommentsLikesRepository;

    public void registerPreferenceCommentsLikes(PreferenceCommentsLikes preferenceCommentsLikes) {
        preferenceCommentsLikesRepository.save(preferenceCommentsLikes);
    }


    public PreferenceCommentsLikes getPreferenceCommentsLikesByCommentsIdAndUserId(Long commentsId, Long userId) {
        return preferenceCommentsLikesRepository.findByPreferenceCommentsIdAndUserId(commentsId, userId);
    }

    public void deletePreferenceCommentsLikeById(Long likesId) {
        preferenceCommentsLikesRepository.deleteById(likesId);
    }

//    public PreferenceComments getPreferenceCommentsById(Long commentsId) {
//        return preferenceCommentsRepository.findById(commentsId).get();
//    }
//
}
