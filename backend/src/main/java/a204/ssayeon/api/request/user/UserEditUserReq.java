package a204.ssayeon.api.request.user;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter //form-data를 받을 때 필요
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEditUserReq {
    private String nickname;
    private MultipartFile picture;
    private List<String> tech_stacks;
    private String company;
}
