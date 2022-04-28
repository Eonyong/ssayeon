package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.AuthDuplicateNicknameReq;
import a204.ssayeon.api.request.AuthJoinReq;
import a204.ssayeon.api.request.AuthVerifyEmailReq;
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
    public AdvancedResponseBody<String> join(@RequestBody AuthJoinReq authJoinReq) {
        return AdvancedResponseBody.of(Status.OK, authService.join(authJoinReq));
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
//    public ResponseEntity<? extends BaseResponseBody> verifyUser() {
//        return ResponseEntity.status(200).body(AdvancedResponseBody.of("success", authService.verifyUser()));
//    }
//
//    @PostMapping("/verify-user-alternate")
//    public ResponseEntity<? extends BaseResponseBody> verifyUserAlternate() {
//        return ResponseEntity.status(200).body(AdvancedResponseBody.of("success", authService.verifyUserAlternate()));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<? extends BaseResponseBody> login() {
//        return ResponseEntity.status(200).body(AdvancedResponseBody.of("success", authService.login()));
//    }



}
