package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.preference.PreferenceApiRequest;
import a204.ssayeon.api.request.preference.PreferenceCommentsApiRequest;
import a204.ssayeon.api.response.preference.PreferenceApiResponse;
import a204.ssayeon.api.response.preference.PreferenceCommentsApiResponse;
import a204.ssayeon.api.response.preference.PreferenceOptionsApiResponse;
import a204.ssayeon.api.service.*;
import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.common.model.response.AdvancedResponseBody;
import a204.ssayeon.common.model.response.PaginationResponseBody;
import a204.ssayeon.config.auth.CurrentUser;
import a204.ssayeon.db.entity.Pagination;
import a204.ssayeon.db.entity.preference.*;
import a204.ssayeon.db.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/preference")
public class PreferenceController {

    private final PreferenceService preferenceService;

    private final PreferenceOptionsService preferenceOptionsService;

    private final PreferenceCommentsService preferenceCommentsService;

    private final PreferenceCommentsLikesService preferenceCommentsLikesService;

    private final PreferenceOptionsUserSelectedService preferenceOptionsUserSelectedService;

    // 선호도 조사 등록
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AdvancedResponseBody<String> registerPreference(@RequestBody PreferenceApiRequest preferenceApiRequest, @CurrentUser User user) {
        Preference preference = Preference.builder()
                .user(user)
                .description(preferenceApiRequest.getDescription())
                .build();
        preferenceService.registerPreference(preference);
        for(String option : preferenceApiRequest.getOptionList()) {
            PreferenceOptions preferenceOptions = PreferenceOptions.builder()
                    .preference(preference)
                    .description(option)
                    .build();
            preferenceOptionsService.registerPreferenceOptions(preferenceOptions);
        }
        return AdvancedResponseBody.of(Status.CREATED);
    }

    // 선호도 조사 1개 불러오기
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{preferenceId}"})
    public AdvancedResponseBody<PreferenceApiResponse> getPreference(@PathVariable Long preferenceId) {
        // 선택지 리스트 먼저 불러오기
        List<PreferenceOptions> options = preferenceOptionsService.getPreferenceOptionsByPreferenceId(preferenceId);
        List<PreferenceOptionsApiResponse> list = new ArrayList<>();
        for(PreferenceOptions option : options) {
            list.add(PreferenceOptionsApiResponse.builder()
                    .preferenceOptionsId(option.getId())
                    .description(option.getDescription())
                    .build());
        }
        Preference preference = preferenceService.getPreferenceById(preferenceId);
        PreferenceApiResponse preferenceApiResponse = PreferenceApiResponse.builder()
            .preferenceId(preferenceId)
            .userId(preference.getUser().getId())
            .writer(preference.getUser().getNickname())
            .preferenceOptionsApiResponseList(list)
            .createAt(preference.getCreatedAt())
            .updatedAt(preference.getUpdatedAt())
            .build();
        return AdvancedResponseBody.of(Status.OK, preferenceApiResponse);
    }

    // 선호도 조사 전체 불러오기
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public AdvancedResponseBody<List<PreferenceApiResponse>> getAllPreferences(@PageableDefault(sort="id",direction = Sort.Direction.DESC,size=10) Pageable pageable) {
        Page<Preference> preferences = preferenceService.getAllPreferences(pageable);
        List<PreferenceApiResponse> list = new ArrayList<>();
        Pagination pagination = Pagination.getPagination(preferences);

        for(Preference preference : preferences) {
            list.add(PreferenceApiResponse.builder()
                    .preferenceId(preference.getId())
                    .userId(preference.getUser().getId())
                    .writer(preference.getUser().getNickname())
                    .preferenceOptionsApiResponseList(null) // 선택지는 필요없으므로 null
                    .createAt(preference.getCreatedAt())
                    .updatedAt(preference.getUpdatedAt())
                    .build());
        }
        return PaginationResponseBody.of(Status.OK, list, pagination);
    }

    // 선호도 조사 수정
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = {"/{preferenceId}"})
    public AdvancedResponseBody<PreferenceApiResponse> modifyPreference(@PathVariable Long preferenceId, @RequestBody PreferenceApiRequest preferenceApiRequest, @CurrentUser User user) {
        Preference preference = preferenceService.getPreferenceById(preferenceId);
        preference.setDescription(preferenceApiRequest.getDescription()); // 질문 변경

        preferenceOptionsService.deletePreferenceOptionsByPreferenceId(preferenceId); // 기존의 선택지 다 지우기
        for(String option : preferenceApiRequest.getOptionList()) { // 새로운 선택지 다 돌면서
            PreferenceOptions preferenceOptions = PreferenceOptions.builder()
                    .preference(preference)
                    .description(option)
                    .build();
            preferenceOptionsService.registerPreferenceOptions(preferenceOptions); // 선택지 저장
        }
        return AdvancedResponseBody.of(Status.OK);
    }

    // 선호도 조사 삭제
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"/{preferenceId}"})
    public AdvancedResponseBody<String> deletePreference(@PathVariable Long preferenceId) {
        preferenceService.deletePreference(preferenceId);
        return AdvancedResponseBody.of(Status.OK);
    }

    // 투표하기
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = {"/{preferenceId}/{preferenceOptionsId}"})
    public AdvancedResponseBody<String> doPoll(@PathVariable Long preferenceId, @PathVariable Long preferenceOptionsId, @CurrentUser User user) {
        PreferenceOptionsUserSelected current = preferenceOptionsUserSelectedService.getPreferenceOptionsUserSelectedByPreferenceIdAndUserId(preferenceId, user.getId());

        if(current!=null) { // 기존의 투표선택지 있으면 삭제
            preferenceOptionsUserSelectedService.deletePreferenceOptionsUserSelectedById(current.getId());
        }
        if(current==null || current.getPreferenceOptions().getId()!=preferenceOptionsId) { // 기존 선택지가 없거나 기존과 다른 선택지를 선택하면
            // 새로운 투표선택지 추가
            PreferenceOptionsUserSelected preferenceOptionsUserSelected = PreferenceOptionsUserSelected.builder()
                    .preference(preferenceService.getPreferenceById(preferenceId))
                    .preferenceOptions(preferenceOptionsService.getPreferenceOptionsById(preferenceOptionsId))
                    .user(user)
                    .build();
            preferenceOptionsUserSelectedService.registerPreferenceOptionsUserSelected(preferenceOptionsUserSelected);
        }
        return AdvancedResponseBody.of(Status.OK);
    }

    // 댓글 생성
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = {"/{preferenceId}/comments"})
    public AdvancedResponseBody<String> registerComment(@PathVariable Long preferenceId, @RequestBody PreferenceCommentsApiRequest preferenceCommentsApiRequest, @CurrentUser User user) {
        PreferenceComments preferenceComments = PreferenceComments.builder()
                .user(user)
                .preference(preferenceService.getPreferenceById(preferenceId))
                .description(preferenceCommentsApiRequest.getDescription()).build();
        preferenceCommentsService.registerPreferenceComments(preferenceComments);
        return AdvancedResponseBody.of(Status.CREATED);
    }

    // 댓글 전체 가져오기
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = {"/{preferenceId}/comments"})
    public AdvancedResponseBody<List> getAllComments(@PathVariable Long preferenceId) {
        List<PreferenceComments> commentsList = preferenceCommentsService.getAllPreferenceCommentsByPreferenceId(preferenceId);
        List<PreferenceCommentsApiResponse> list = new ArrayList<>();
        for(PreferenceComments comments : commentsList) {
            PreferenceCommentsApiResponse preferenceCommentsApiResponse = PreferenceCommentsApiResponse.builder()
                    .preferenceCommentsId(comments.getId())
                    .userId(comments.getUser().getId())
                    .writer(comments.getUser().getNickname())
                    .description(comments.getDescription())
                    .createAt(comments.getCreatedAt())
                    .updatedAt(comments.getUpdatedAt())
                    .build();
            list.add(preferenceCommentsApiResponse);
        }
        return AdvancedResponseBody.of(Status.OK, list);
    }

    // 댓글 수정
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = {"/{preferenceId}/comments/{commentsId}"})
    public AdvancedResponseBody<String> modifyPreferenceComments(@PathVariable Long commentsId, @RequestBody PreferenceCommentsApiRequest preferenceCommentsApiRequest) {
        PreferenceComments preferenceComments = preferenceCommentsService.getPreferenceCommentsById(commentsId);
        preferenceComments.setDescription(preferenceCommentsApiRequest.getDescription()); // 내용 수정
        preferenceCommentsService.registerPreferenceComments(preferenceComments);
        return AdvancedResponseBody.of(Status.OK);
    }

    // 댓글 삭제
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"/{preferenceId}/comments/{commentsId}"})
    public AdvancedResponseBody<String> deletePreferenceComments(@PathVariable Long commentsId) {
        preferenceCommentsService.deletePreferenceCommentsById(commentsId);
        return AdvancedResponseBody.of(Status.OK);
    }

    // 댓글 좋아요 및 취소
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = {"/{preferenceId}/comments/{commentsId}/like"})
    public AdvancedResponseBody<String> likeComment(@PathVariable Long commentsId, @CurrentUser User user) {
        // 좋아요
        PreferenceCommentsLikes currentLike = preferenceCommentsLikesService.getPreferenceCommentsLikesByCommentsIdAndUserId(commentsId, user.getId());
        if(currentLike==null) {
            PreferenceCommentsLikes preferenceCommentsLikes = PreferenceCommentsLikes.builder()
                    .preferenceComments(preferenceCommentsService.getPreferenceCommentsById(commentsId))
                    .user(user)
                    .build();
           preferenceCommentsLikesService.registerPreferenceCommentsLikes(preferenceCommentsLikes);
        }else { // 좋아요 취소
            preferenceCommentsLikesService.deletePreferenceCommentsLikeById(currentLike.getId());
        }
        return AdvancedResponseBody.of(Status.OK);
    }

    // 검색
    @GetMapping(value = {"/search/type"})
    public AdvancedResponseBody<String> searchPreference(@PathVariable Integer type, @RequestParam String query) {
        preferenceService.searchPreferences(type,query);
        return AdvancedResponseBody.of(Status.OK);
    }
}