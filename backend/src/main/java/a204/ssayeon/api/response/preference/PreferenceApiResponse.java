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
public class PreferenceApiResponse {

    private Long preferenceId; // PK
    private Long userId; // 유저 id
    private LocalDateTime createAt; // 작성일자
    private LocalDateTime modifiedAt; // 수정일자

}
