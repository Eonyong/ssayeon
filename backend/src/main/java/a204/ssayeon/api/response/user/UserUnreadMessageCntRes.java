package a204.ssayeon.api.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUnreadMessageCntRes {
    private Integer unreadMessageCnt;
}
