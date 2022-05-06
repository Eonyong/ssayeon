package a204.ssayeon.api.request.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEditUserReq {
    private String nickname;
    private MultipartFile picture;
    private List<String> techStacks;
    private String company;
}
