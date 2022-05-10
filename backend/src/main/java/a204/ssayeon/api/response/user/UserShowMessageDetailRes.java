package a204.ssayeon.api.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserShowMessageDetailRes {
    private Long id;
    private Long sender_id;
    private String sender_nickname;
    private Long receiver_id;
    private String receiver_nickname;
    private String description;
    private LocalDateTime created_at;
}
