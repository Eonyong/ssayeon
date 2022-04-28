package a204.ssayeon.api.request;

import a204.ssayeon.db.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthJoinReq {
    private String nickname;
    private String name;
    private String email;
    private String classId;

    private String password;

    public User toUser(String encodingPasswrod) {
        return User.builder().nickname(nickname).name(name).email(email).classId(classId).password(encodingPasswrod).build();
    }
}
