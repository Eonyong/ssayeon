package a204.ssayeon.api.response.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationShowListRes {
    private Long id;
    private String title;
    private Integer views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
