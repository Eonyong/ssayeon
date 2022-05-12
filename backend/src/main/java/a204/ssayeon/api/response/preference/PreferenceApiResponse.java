package a204.ssayeon.api.response.preference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreferenceApiResponse {

    private Long preferenceId; // PK
    private Long userId; // 유저 id
    private String writer; // 유저 이름
    private String description;
    private List<PreferenceOptionsApiResponse> preferenceOptionsApiResponseList; // 선택지 리스트
    private LocalDateTime createAt; // 작성일자
    private LocalDateTime updatedAt; // 수정일자

}
