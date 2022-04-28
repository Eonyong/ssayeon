package a204.ssayeon.api.service;

import a204.ssayeon.common.exceptions.AlreadyExistException;
import a204.ssayeon.common.model.enums.ErrorMessage;
import a204.ssayeon.db.entity.user.User;
import a204.ssayeon.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Transactional(readOnly = true)
    public void duplicateNickname(String nickname) {
        Optional<User> byNickname = userRepository.findByNickname(nickname);
        if(byNickname.isPresent()){
            throw new AlreadyExistException(ErrorMessage.USER_ALREADY_EXIST);
        }
    }

    @Transactional(readOnly = true)
    public String verifyEmail(String email) throws Exception {
        duplicateEmail(email);
        String code = "";
        code = emailService.sendSimpleMessage(email, ""); // 이메일 인증 코드 보내기

        return code;
    }

    public void duplicateEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if(byEmail.isPresent()){
            throw new AlreadyExistException(ErrorMessage.USER_ALREADY_EXIST);
        }
    }

}
