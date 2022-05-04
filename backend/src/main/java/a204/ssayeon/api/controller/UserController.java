package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.alarm.UserAlarmReadReq;
import a204.ssayeon.api.service.UserService;
import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.common.model.response.AdvancedResponseBody;
import a204.ssayeon.config.auth.CurrentUser;
import a204.ssayeon.db.entity.user.Alarm;
import a204.ssayeon.db.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public AdvancedResponseBody<String> deleteUser(@CurrentUser User user) {
        userService.deleteUser(user);
        return AdvancedResponseBody.of(Status.NO_CONTENT);
    }

    @GetMapping("/alarm") //todo : currentUser 없으면 에러 던지기
    public AdvancedResponseBody<Page<List<Alarm>>> showAlarm(@CurrentUser User user, @PageableDefault(sort="id", direction= Sort.Direction.DESC) Pageable pageable) {
        return AdvancedResponseBody.of(Status.OK, userService.showAlarm(user, pageable));
    }

    @PostMapping("/send-alarm") //todo : user 두 번 select 하는 이유는?
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
