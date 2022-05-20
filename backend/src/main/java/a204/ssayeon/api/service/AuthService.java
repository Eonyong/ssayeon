package a204.ssayeon.api.service;

import a204.ssayeon.api.request.auth.AuthJoinReq;
import a204.ssayeon.api.request.auth.AuthLoginReq;
import a204.ssayeon.api.request.auth.AuthVerifyUserReq;
import a204.ssayeon.api.response.auth.AuthJoinRes;
import a204.ssayeon.common.exceptions.AlreadyExistException;
import a204.ssayeon.common.exceptions.NotExistException;
import a204.ssayeon.common.exceptions.UnAuthorizedException;
import a204.ssayeon.common.model.enums.ErrorMessage;
import a204.ssayeon.config.auth.CustomAuthenticationProvider;
import a204.ssayeon.config.jwt.TokenProvider;
import a204.ssayeon.db.entity.user.User;
import a204.ssayeon.db.repository.user.UserCertificationRepository;
import a204.ssayeon.db.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticationProvider authenticationManager;
    private final TokenProvider tokenProvider;
    private final UserCertificationRepository userCertificationRepository;


    public AuthJoinRes join(AuthJoinReq authJoinReq) {
        duplicateEmail(authJoinReq.getEmail());
        duplicateNickname(authJoinReq.getNickname());

        //패스워드 암호화
        User user = authJoinReq.toUser(passwordEncoder.encode(authJoinReq.getPassword()));
        userRepository.save(user);

        return AuthJoinRes.builder().nickname(user.getNickname()).build();
    }

    @Transactional(readOnly = true)
    public void duplicateNickname(String nickname) {
        Optional<User> byNickname = userRepository.findByNickname(nickname);
        if(byNickname.isPresent()){
            throw new AlreadyExistException(ErrorMessage.NICKNAME_ALREADY_EXIST);
        }
    }

    @Transactional(readOnly = true)
    public String verifyEmail(String email) throws Exception {
        duplicateEmail(email);
        String code = "";
        code = emailService.sendSimpleMessage(email, ""); // 이메일 인증 코드 보내기

        return code;
    }


    @Transactional(readOnly = true)
    public void verifyUser(AuthVerifyUserReq authVerifyUserReq) {
        boolean existsByNameAndClassId = userCertificationRepository.existsByNameAndClassId(authVerifyUserReq.getName(), authVerifyUserReq.getClassId());

        if(!existsByNameAndClassId)
            throw new NotExistException(ErrorMessage.USER_CERTIFICATION_INCORRECT);
    }

//    @Transactional(readOnly = true)
//    public void verifyUserAlternate() {
//        return ;
//    }

    @Transactional(readOnly = true)
    public String login(AuthLoginReq authLoginReq) {
        Authentication authentication = matchIdAndPassword(authLoginReq);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createToken(authentication);
    }

    public void duplicateEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if(byEmail.isPresent()){
            throw new AlreadyExistException(ErrorMessage.EMAIL_ALREADY_EXIST);
        }
    }

    private Authentication matchIdAndPassword(AuthLoginReq authLoginReq) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authLoginReq.getEmail(),
                        authLoginReq.getPassword()
                ));
        return authentication;
    }

    public void throwUser() {
        throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED);
    }
}
