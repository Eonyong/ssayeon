package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.AuthDuplicateNicknameReq;
import a204.ssayeon.api.request.AuthVerifyEmailReq;
import a204.ssayeon.api.service.AuthService;
import a204.ssayeon.common.model.response.AdvancedResponseBody;
import a204.ssayeon.common.model.response.BaseResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/duplicate-nickname")
    public ResponseEntity<? extends BaseResponseBody> duplicateNickname(@RequestBody AuthDuplicateNicknameReq authDuplicateNicknameReq) {
        authService.duplicateNickname(authDuplicateNicknameReq.getNickname());
        return ResponseEntity.status(200).body(new BaseResponseBody(200, "사용 가능한 닉네임입니다"));
    }

    @PostMapping("/verify-email")
    public ResponseEntity<? extends BaseResponseBody> verifyEmail(@RequestBody AuthVerifyEmailReq authVerifyEmailReq) throws Exception {
        return ResponseEntity.status(200).body(new AdvancedResponseBody(200, "사용 가능한 이메일입니다", authService.verifyEmail(authVerifyEmailReq.getEmail())));
    }

}
