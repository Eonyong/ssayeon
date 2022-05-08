package a204.ssayeon.api.request.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFindUserRes {
    Long id;
    String nickname;
    String picture;
}
