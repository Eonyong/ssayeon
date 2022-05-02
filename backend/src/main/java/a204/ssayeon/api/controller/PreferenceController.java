package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.preference.PreferenceApiRequest;
import a204.ssayeon.api.response.preference.PreferenceApiResponse;
import a204.ssayeon.api.service.PreferenceService;
import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.common.model.response.AdvancedResponseBody;
import a204.ssayeon.db.entity.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
//@CrossOrigin("*")
@RequestMapping("/preference")
public class PreferenceController {

    @Autowired
    private PreferenceService preferenceService;

    // 게임 등록
    @PostMapping
    public AdvancedResponseBody<String> registerPreference(@RequestBody PreferenceApiRequest preferenceApiRequest) {
        Preference preference = Preference.builder()
                .id(preferenceApiRequest.getPreferenceId())
                // user 넣기
                .description(preferenceApiRequest.getDescription())
                .build();
        preference.setCreatedAt(LocalDateTime.now());
        preferenceService.registerPreference(preference);
        return AdvancedResponseBody.of(Status.OK);
    }

    //    게임 읽기
    @GetMapping(value = {"/{preference_id}"})
    public AdvancedResponseBody<String> getGame() {
        return AdvancedResponseBody.of(Status.OK);

    }

    // 전체 불러오기
    @GetMapping
    public AdvancedResponseBody<List<PreferenceApiResponse>> getAllPreference(){
        List<Preference> preferences = preferenceService.getAllPreferences();
        List<PreferenceApiResponse> list = new ArrayList<>();
        for(Preference preference : preferences) {
            list.add(PreferenceApiResponse.builder()
                    .preferenceId(preference.getId())
                    .userId(preference.getUser().getId())
                    .createAt(preference.getCreatedAt())
                    .modifiedAt(preference.getUpdatedAt())
                    .build());
        }
        return AdvancedResponseBody.of(Status.OK, list);
    }

    //    게임 삭제
    @DeleteMapping(value = {"/{preference_id}"})
    public AdvancedResponseBody<String> deleteGame(@PathVariable Long preferenceId) {
        preferenceService.deletePreference(preferenceId);
        return AdvancedResponseBody.of(Status.OK);
    }

    //    투표하기
    @PostMapping(value = {"/{preference_id}/{poll_id}"})
    public AdvancedResponseBody<String> poll() {
        return AdvancedResponseBody.of(Status.OK);
    }

    //    게임 결과 반환
    @GetMapping(value = {"/{preference_id}/statistics"})
    public AdvancedResponseBody<String> getGameResult() {
        return AdvancedResponseBody.of(Status.OK);
    }

    //    댓글 생성
    @PostMapping(value = {"/{preference_id}/comments"})
    public AdvancedResponseBody<String> registerComment() {
        return AdvancedResponseBody.of(Status.OK);
    }

    //    댓글 수정
    @PatchMapping(value = {"/{preference_id}/comments/{comments_id}"})
    public AdvancedResponseBody<String> modifyComment() {
        return AdvancedResponseBody.of(Status.OK);
    }

    //    댓글 가져오기
    @GetMapping(value = {"/{preference_id}/comments"})
    public AdvancedResponseBody<String> getAllComments() {
        return AdvancedResponseBody.of(Status.OK);
    }

    //    댓글 삭제
    @DeleteMapping(value = {"/{preference_id}/comments/{comments_id}"})
    public AdvancedResponseBody<String> deleteComment() {
        return AdvancedResponseBody.of(Status.OK);
    }

    //    댓글 좋아요
    @PostMapping(value = {"/{preference_id}/comments/likes/{comments_id}"})
    public AdvancedResponseBody<String> likeComment() {
        return AdvancedResponseBody.of(Status.OK);
    }

    //    검색
    @GetMapping(value = {"/search"})
    public AdvancedResponseBody<String> searchPreference() {
        return AdvancedResponseBody.of(Status.OK);
    }
}
