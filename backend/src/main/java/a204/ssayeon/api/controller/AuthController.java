package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.auth.*;
import a204.ssayeon.api.response.auth.AuthJoinRes;
import a204.ssayeon.api.service.AuthService;
import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.common.model.response.AdvancedResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/join")
    public AdvancedResponseBody<AuthJoinRes> join(@RequestBody AuthJoinReq authJoinReq) {
        return AdvancedResponseBody.of(Status.CREATED, authService.join(authJoinReq));
    }

    @PostMapping("/duplicate-nickname")
    public AdvancedResponseBody<String> duplicateNickname(@RequestBody AuthDuplicateNicknameReq authDuplicateNicknameReq) {
        authService.duplicateNickname(authDuplicateNicknameReq.getNickname());
        return AdvancedResponseBody.of(Status.OK);
    }

    @PostMapping("/verify-email")
    public AdvancedResponseBody<String> verifyEmail(@RequestBody AuthVerifyEmailReq authVerifyEmailReq) throws Exception {
        return AdvancedResponseBody.of(Status.OK, authService.verifyEmail(authVerifyEmailReq.getEmail()));
    }

    @PostMapping("/verify-user")
    public AdvancedResponseBody<String> verifyUser(@RequestBody AuthVerifyUserReq authVerifyUserReq) {
        authService.verifyUser(authVerifyUserReq);
        return AdvancedResponseBody.of(Status.OK);
    }

//    @PostMapping("/verify-user-alternate")
//    public AdvancedResponseBody<String> verifyUserAlternate() {
//        return AdvancedResponseBody.of(Status.OK, authService.verifyUserAlternate());
//    }

    @PostMapping("/login")
    public AdvancedResponseBody<String> login(@RequestBody AuthLoginReq authLoginReq) {
        return AdvancedResponseBody.of(Status.OK, authService.login(authLoginReq));
    }

}
