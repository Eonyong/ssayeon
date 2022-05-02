package a204.ssayeon.config.auth;

import a204.ssayeon.common.exceptions.NotJoinedUserException;
import a204.ssayeon.common.model.enums.ErrorMessage;
import a204.ssayeon.db.entity.user.User;
import a204.ssayeon.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service //IoC //로그인 요청하면 실행
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    //1. 패스워드는 알아서 체킹하니까 신경 x
    //2. 리턴이 잘 되면 자동으로 UserDetails타입 세션 만듦

    @Override
    @Transactional
    public PrincipalDetails loadUserByUsername(String email){
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NotJoinedUserException(ErrorMessage.USER_EMAIL_INCORRET)
        );
        return new PrincipalDetails(user);
    }

    @Transactional
    public PrincipalDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id " + id + "를 가진 사용자가 없습니다.")
        );

        return new PrincipalDetails(user);
    }
}
