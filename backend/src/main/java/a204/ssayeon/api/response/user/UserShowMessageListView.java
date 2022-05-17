package a204.ssayeon.api.response.user;

import java.time.LocalDateTime;

public interface UserShowMessageListView {
    Long getMessageId();
    Long getSenderId();
    String getSenderNickname();
    String getDescription();
    LocalDateTime getCreatedAt();
    Integer getUnReadCnt();
    Long getOtherUserId();
}
