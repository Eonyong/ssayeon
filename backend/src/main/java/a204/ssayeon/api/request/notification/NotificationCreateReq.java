package a204.ssayeon.api.request.notification;

import a204.ssayeon.db.entity.notification.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationCreateReq {
    private String title;
    private String description;

    public Notification toNotification(){
        return Notification.builder().title(this.title).description(this.description).build();
    }

}
