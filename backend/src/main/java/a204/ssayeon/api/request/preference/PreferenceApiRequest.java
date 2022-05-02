package a204.ssayeon.api.request.preference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreferenceApiRequest {

    private Long preferenceId; // PK
    private Long userId; // 유저 id
    private String description; // 선호도조사 질문
    private List<String> list; // 선택지 리스트

}
