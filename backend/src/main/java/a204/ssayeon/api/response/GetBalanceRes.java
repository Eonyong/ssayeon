package a204.ssayeon.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetBalanceRes {
    private Long balanceId;
    private Long userId;
    private String description;
    private String leftDescription;
    private String rightDescription;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
