package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.user.*;
import a204.ssayeon.api.response.user.*;
import a204.ssayeon.api.service.UserService;
import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.common.model.response.AdvancedResponseBody;
import a204.ssayeon.common.model.response.PaginationResponseBody;
import a204.ssayeon.config.auth.CurrentUser;
import a204.ssayeon.db.entity.Pagination;
import a204.ssayeon.db.entity.user.Alarm;
import a204.ssayeon.db.entity.user.Message;
import a204.ssayeon.db.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public AdvancedResponseBody<UserShowUserRes> showUser(@CurrentUser User user, @PathVariable Long id){
        return AdvancedResponseBody.of(Status.OK, userService.showUser(id));
    }

    @GetMapping("/mypage")
    public AdvancedResponseBody<UserShowMyPageRes> showMyPage(@CurrentUser User user){
        return AdvancedResponseBody.of(Status.OK, userService.showMyPage(user));
    }

    @GetMapping("/{id}/activity")
    public AdvancedResponseBody<List<UserShowUserActivityRes>> showUserActivity(@PathVariable Long id, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size=10) Pageable pageable){
        Object[] objects = userService.showUserActivity(id, pageable);
        Pagination pagination = (Pagination) objects[0];
        List<UserShowUserActivityRes> articleList = (List<UserShowUserActivityRes>) objects[1];

        return PaginationResponseBody.of(Status.OK, articleList, pagination);
    }

    @GetMapping("/message/unread-cnt")
    public AdvancedResponseBody<UserUnreadMessageCntRes> unreadMessageCnt(@CurrentUser User user){
        return AdvancedResponseBody.of(Status.OK, userService.unreadMessageCnt(user));
    }

    @GetMapping("/message/list")
    public AdvancedResponseBody<List<UserShowMessageListRes>> showMessageList(@CurrentUser User user, @PageableDefault(sort = "message_id", direction = Sort.Direction.DESC, size=10) Pageable pageable){
        Page<UserShowMessageListView> MessagePage = userService.showMessageList(user, pageable);
        Pagination pagination = Pagination.getPagination(MessagePage);
        List<UserShowMessageListRes> messageList = new ArrayList<>();

        MessagePage.forEach((message) -> {
            messageList.add(UserShowMessageListRes.builder().createdAt(message.getCreatedAt()).description(message.getDescription()).id(message.getMessageId())
                    .senderId(message.getSenderId()).senderNickname(message.getSenderNickname()).unReadCnt(message.getUnReadCnt()).otherUserId(message.getOtherUserId()).build());
        });
        return PaginationResponseBody.of(Status.OK, messageList, pagination);

    }

    @GetMapping("/message/{otherUserId}")
    public AdvancedResponseBody<List<UserShowMessageDetailRes>> showMessageDetail(@CurrentUser User user, @PathVariable Long otherUserId, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size=10) Pageable pageable){
        Page<Message> MessagePage = userService.showMessageDetail(user, otherUserId, pageable);
        Pagination pagination = Pagination.getPagination(MessagePage);
        List<UserShowMessageDetailRes> messageList = new ArrayList<>();

        MessagePage.forEach((message) -> {
            messageList.add(UserShowMessageDetailRes.builder().createdAt(message.getCreatedAt()).description(message.getDescription()).id(message.getId())
                    .receiverId(message.getReceiver().getId()).receiverNickname(message.getReceiver().getNickname()).senderId(message.getSender().getId()).senderNickname(message.getSender().getNickname()).isRead(message.getIsRead()).build());
        });
        return PaginationResponseBody.of(Status.OK, messageList, pagination);
    }

    @PostMapping("/message/{toUserId}")
    public AdvancedResponseBody<String> sendMessage(@CurrentUser User user, @PathVariable Long toUserId, @RequestBody UserSendMessageReq userSendMessageReq){
        userService.sendMessage(user, toUserId, userSendMessageReq.getDescription());
        return AdvancedResponseBody.of(Status.OK);
    }


    @PatchMapping("/{id}")
    public AdvancedResponseBody<String> editUser(@CurrentUser User user, @ModelAttribute UserEditUserReq userEditUserReq) throws IOException {
        userService.editUser(user, userEditUserReq);
        return AdvancedResponseBody.of(Status.OK);
    }

    @PatchMapping("/{id}/password")
    public AdvancedResponseBody<String> editPassword(@CurrentUser User user, @RequestBody UserEditPasswordReq userEditPasswordReq) {
        userService.editPassword(user, userEditPasswordReq);
        return AdvancedResponseBody.of(Status.OK);
    }

    @GetMapping("/search")
    public AdvancedResponseBody<List<UserFindUserRes>> findUser(@RequestParam String word, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size=10) Pageable pageable) {
        Page<User> userPage = userService.findUser(word, pageable);
        Pagination pagination = Pagination.getPagination(userPage);
        List<UserFindUserRes> userList = new ArrayList<>();

        userPage.forEach((user) -> {
            userList.add(UserFindUserRes.builder().nickname(user.getNickname()).id(user.getId()).picture(user.getPicture()).build());
        });
        return PaginationResponseBody.of(Status.OK, userList, pagination);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public AdvancedResponseBody<String> deleteUser(@CurrentUser User user) {
        userService.deleteUser(user);
        return AdvancedResponseBody.of(Status.NO_CONTENT);
    }

    @GetMapping("/alarm")
    public AdvancedResponseBody<List<UserShowAlarmRes>> showAlarm(@CurrentUser User user, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size=10) Pageable pageable) {
        Page<Alarm> alarmPage = userService.showAlarm(user, pageable);
        Pagination pagination = Pagination.getPagination(alarmPage);
        List<UserShowAlarmRes> alarmList = new ArrayList<>();

        alarmPage.forEach((alarm) -> {
            alarmList.add(UserShowAlarmRes.builder().description(alarm.getDescription()).id(alarm.getId()).isRead(alarm.getIsRead()).url(alarm.getUrl()).build());
        });
        return PaginationResponseBody.of(Status.OK, alarmList, pagination);
    }

    @PostMapping("/send-alarm")
    public AdvancedResponseBody<String> sendAlarm(@CurrentUser User user) {
        userService.sendAlarm(user);
        return AdvancedResponseBody.of(Status.OK);
    }

    @PostMapping("/read-alarm")
    public AdvancedResponseBody<String> readAlarm(@RequestBody UserAlarmReadReq alarmReadReq) {
        userService.readAlarm(alarmReadReq.getAlarmId());
        return AdvancedResponseBody.of(Status.OK);
    }
}
