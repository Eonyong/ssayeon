package a204.ssayeon.api.response.preference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreferenceCommentsApiResponse {

    private Long preferenceCommentsId; // PK
    private Long userId; // 유저 id
    private String writer; // 유저 닉네임
    private String description; // 댓글 내용
    private LocalDateTime createAt; // 작성일자
    private LocalDateTime updatedAt; // 수정일자

}
