package a204.ssayeon.api.request.preference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreferenceCommentsApiRequest {

    private String description; // 댓글 내용

}
