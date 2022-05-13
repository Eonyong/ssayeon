package a204.ssayeon.db.repository.user;

import a204.ssayeon.api.response.user.UserShowMessageListRes;
import a204.ssayeon.api.response.user.UserShowMessageListView;
import a204.ssayeon.db.entity.user.Message;
import a204.ssayeon.db.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.sender.id=:userId AND m.receiver.id=:otherUserId OR m.sender.id=:otherUserId AND m.receiver.id=:userId ORDER BY m.createdAt DESC")
    Page<Message> findByIdAndSenderOrReceiver(Long userId, Long otherUserId, Pageable pageable);

    //1. sender나 receiver가 나인거 뽑아오기
    //2. sender나 receiver가 내가 아닌 다른 거 뽑아오기
    //3. 최신글 가져오기기

    //필요한 데이터
    //마지막 메시지 유저의 id, 닉네임, 메시지 내용
    //상대 유저의 id //other_user_id
    @Query(value = "SELECT m.message_id as messageId, m.sender_id as senderId, u.nickname as senderNickname, m.description, m.created_at as createdAt, rt.group_cnt as unReadCnt, rt.other_id as otherUserId FROM " +
                    "   (SELECT " +
                    "       (CASE WHEN sender_id!=:senderId THEN sender_id ELSE receiver_id END) other_id, MAX(message_id) max_id, COUNT(case when is_read=0 and receiver_id=:senderId then 1 end) group_cnt " +
                    "           FROM message WHERE sender_id=:senderId OR receiver_id=:receiverId GROUP BY other_id) rt " +
                    "LEFT JOIN message m ON m.message_id=rt.max_id " +
                    "LEFT JOIN user u ON m.sender_id=u.user_id " +
                    "LEFT JOIN user other_u ON rt.other_id=other_u.user_id",
            countQuery = "SELECT COUNT(*) FROM " +
                    "       (SELECT " +
                    "           (CASE WHEN sender_id!=:senderId THEN sender_id ELSE receiver_id END) other_id" +
                    "                    FROM message WHERE sender_id=:senderId OR receiver_id=:receiverId GROUP BY other_id) rt",
            nativeQuery = true)
    Page<UserShowMessageListView> findByMessageListSenderOrReceiver(Long senderId, Long receiverId, Pageable pageable);

    @Modifying
    @Query("UPDATE Message m SET m.isRead=true WHERE m.receiver.id=:receiverId AND m.sender.id=:senderId AND m.isRead=false")
    void updateByReceiverIdAndSenderIdAndIsReadIsFalseToTrue(Long receiverId, Long senderId);

    @Query("SELECT COUNT(m) FROM Message m WHERE m.receiver=:user AND m.isRead=false")
    int findByReceiverAndIsReadIsFalseCnt(User user);

}
