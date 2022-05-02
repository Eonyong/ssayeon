package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.auth.AuthDuplicateNicknameReq;
import a204.ssayeon.api.request.auth.AuthJoinReq;
import a204.ssayeon.api.request.auth.AuthLoginReq;
import a204.ssayeon.api.request.auth.AuthVerifyEmailReq;
import a204.ssayeon.api.response.auth.AuthJoinRes;
import a204.ssayeon.api.service.AuthService;
import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.common.model.response.AdvancedResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

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

//    @PostMapping("/verify-user")
//    public AdvancedResponseBody<String> verifyUser() {
//        return AdvancedResponseBody.of(Status.OK, authService.verifyUser());
//    }
//
//    @PostMapping("/verify-user-alternate")
//    public AdvancedResponseBody<String> verifyUserAlternate() {
//        return AdvancedResponseBody.of(Status.OK, authService.verifyUserAlternate());
//    }

    @PostMapping("/login")
    public AdvancedResponseBody<String> login(@RequestBody AuthLoginReq authLoginReq) {
        return AdvancedResponseBody.of(Status.OK, authService.login(authLoginReq));
    }

}
