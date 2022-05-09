package a204.ssayeon.api.response.balance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalanceCommentsRes {
    private Long BalanceCommentsId;
    private Long userId;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int likes;
}
