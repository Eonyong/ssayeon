package a204.ssayeon.api.response.preference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreferenceOptionsApiResponse {

    private Long preferenceId; // PK
    private String description; // 선택지 내용

}
