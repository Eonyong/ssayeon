package a204.ssayeon.api.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserShowAlarmRes {
    private Long id;
    private String description;
    private String url;
    private Boolean isRead;
}