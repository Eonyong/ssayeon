package a204.ssayeon.api.service;

import a204.ssayeon.api.request.user.UserEditPasswordReq;
import a204.ssayeon.api.request.user.UserEditUserReq;
import a204.ssayeon.common.exceptions.NotJoinedUserException;
import a204.ssayeon.common.model.enums.ErrorMessage;
import a204.ssayeon.db.entity.user.Alarm;
import a204.ssayeon.db.entity.user.TechStack;
import a204.ssayeon.db.entity.user.User;
import a204.ssayeon.db.entity.user.UserHasTechStack;
import a204.ssayeon.db.repository.user.AlarmRepository;
import a204.ssayeon.db.repository.user.TechStackRepository;
import a204.ssayeon.db.repository.user.UserHasTechStackRepository;
import a204.ssayeon.db.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TechStackRepository techStackRepository;
    private final UserHasTechStackRepository userHasTechStackRepository;


    @Transactional
    public void editUser(User user, UserEditUserReq userEditUserReq) {
        if(userEditUserReq.getNickname()!=null)
            user.setNickname(user.getNickname());
        if(userEditUserReq.getPicture()!=null) //todo
            user.setPicture(user.getPicture());
        if(userEditUserReq.getCompany()!=null)
            user.setCompany(user.getNickname());
        if(userEditUserReq.getTechStacks()!=null){
            List<String> techStacks = userEditUserReq.getTechStacks();

            //userHasTechStack에 저장된 거 다 날리고 새로 저장
            List<UserHasTechStack> userHasTechStacks = userHasTechStackRepository.findByUser(user);
            userHasTechStackRepository.deleteAll(userHasTechStacks);

            techStacks.forEach(ts -> {
                Optional<TechStack> opTechStack = techStackRepository.findByDescription(ts);
                if (opTechStack.isPresent()) {
                    TechStack techStack = opTechStack.get();
                    Optional<UserHasTechStack> opUserHasTechStack = userHasTechStackRepository.findByUserAndTechStack(user, techStack);
                    if (opUserHasTechStack.isEmpty()) { //기술스택 저장 & 유저에 저장 안 됨
                        userHasTechStackRepository.save(UserHasTechStack.builder().techStack(techStack).user(user).build());
                    }

                }
//                else {
//                    techStackRepository.save(TechStack.builder().description(ts).build());
//                }

            });
        }
    }

    @Transactional
    public void editPassword(User user, UserEditPasswordReq userEditPasswordReq) {
        User userEntity = userRepository.findByEmailAndPassword(user.getEmail(), passwordEncoder.encode(userEditPasswordReq.getCurrPassword())).orElseThrow(
                () -> new NotJoinedUserException(ErrorMessage.USER_PASSWORD_INCORRET)
        );

        userEntity.setPassword(passwordEncoder.encode(userEditPasswordReq.getNewPassword()));
    }

    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public Page<Alarm> showAlarm(User user, Pageable pageable) {
        return alarmRepository.findByUser(user, pageable);
    }

    @Transactional
    public void sendAlarm(User user) {
        user.setIsAlarm();
        userRepository.save(user);
    }

    @Transactional
    public void readAlarm(Long alarmId) {
        alarmRepository.updateRead(alarmId);
    }
}
