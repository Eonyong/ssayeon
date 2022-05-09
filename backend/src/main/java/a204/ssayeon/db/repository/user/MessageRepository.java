package a204.ssayeon.db.repository.user;

import a204.ssayeon.api.response.user.UserShowMessageDetail;
import a204.ssayeon.api.response.user.UserShowMessageList;
import a204.ssayeon.db.entity.user.Message;
import a204.ssayeon.db.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.sender.id=:userId AND m.receiver.id=:otherUserId OR m.sender.id=:otherUserId AND m.receiver.id=:userId ORDER BY m.createdAt DESC")
    Page<Message> findByIdAndSenderOrReceiver(Long userId, Long otherUserId, Pageable pageable);

    //1. sender나 receiver가 나인거 뽑아오기
    //2. sender나 receiver가 내가 아닌 다른 거 뽑아오기
    //3. 최신글 가져오기기
    @Query(value = "SELECT m.message_id, m.description, m.sender_id, m.receiver_id, m.created_at, m.updated_at FROM " +
                    "   (SELECT " +
                    "       (CASE WHEN sender_id!=:senderId THEN sender_id ELSE receiver_id END) other_id, MAX(message_id) max_id " +
                    "           FROM message WHERE sender_id=:senderId OR receiver_id=:receiverId GROUP BY other_id) rt " +
                    "LEFT JOIN message m ON m.message_id=rt.max_id",
            countQuery = "SELECT COUNT(*) FROM " +
                    "       (SELECT " +
                    "           (CASE WHEN sender_id!=:senderId THEN sender_id ELSE receiver_id END) other_id" +
                    "                    FROM message WHERE sender_id=:senderId OR receiver_id=:receiverId GROUP BY other_id) rt",
            nativeQuery = true)
    Page<Message> findByMessageListSenderOrReceiver(Long senderId, Long receiverId, Pageable pageable);


}
