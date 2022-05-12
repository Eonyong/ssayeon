package a204.ssayeon.api.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserShowUserRes {
    Long id;
    String nickname;
    String company;
    List<String> techStacks;
    String picture;

}
