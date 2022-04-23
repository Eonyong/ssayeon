package a204.ssayeon.domain.user;

import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String nickname;
    private String email;
    private String class_id;
    private String password;
    private String company;
    private String picture;
}
