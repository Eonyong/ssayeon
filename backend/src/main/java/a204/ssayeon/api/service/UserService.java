package a204.ssayeon.api.service;

import a204.ssayeon.api.request.user.UserEditPasswordReq;
import a204.ssayeon.api.request.user.UserEditUserReq;
import a204.ssayeon.common.exceptions.NotJoinedUserException;
import a204.ssayeon.common.model.enums.ErrorMessage;
import a204.ssayeon.config.aws.S3Util;
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

import java.io.IOException;
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

    private final S3Util s3util;


    @Transactional
    public void editUser(User user, UserEditUserReq userEditUserReq) throws IOException {
        if (userEditUserReq.getNickname() != null)
            user.setNickname(userEditUserReq.getNickname());
        if (!userEditUserReq.getPicture().isEmpty()) {
            if(user.getPicture()!=null) {
                s3util.fileDelete(user.getPicture());
            }
            String imgUrl = s3util.upload(userEditUserReq.getPicture(), "profile"); //s3 업로드
            user.setPicture(imgUrl);
        }
        if (userEditUserReq.getCompany() != null)
            user.setCompany(userEditUserReq.getCompany());
        if (userEditUserReq.getTech_stacks() != null) {
            List<String> techStacks = userEditUserReq.getTech_stacks();

            //userHasTechStack에 저장된 거 날리고 새로 저장
            List<UserHasTechStack> userHasTechStacks = userHasTechStackRepository.findByUser(user);
            userHasTechStackRepository.deleteAll(userHasTechStacks);

            techStacks.forEach(ts -> {
                TechStack techStack = null;
                Optional<TechStack> byDescription = techStackRepository.findByDescription(ts);
                if (byDescription.isEmpty())
                    techStack = techStackRepository.save(TechStack.builder().description(ts).build());
                else
                    techStack = byDescription.get();

                userHasTechStackRepository.save(UserHasTechStack.builder().user(user).techStack(techStack).build());
            });
        }
        userRepository.save(user);
    }

    @Transactional
    public void editPassword(User user, UserEditPasswordReq userEditPasswordReq) {
        User userEntity = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new NotJoinedUserException(ErrorMessage.USER_CERTIFICATION_INCORRECT)
        );

        boolean matches = passwordEncoder.matches(userEditPasswordReq.getCurrPassword(), userEntity.getPassword());
        if (matches)
            userEntity.setPassword(passwordEncoder.encode(userEditPasswordReq.getNewPassword()));
        else
            throw new NotJoinedUserException(ErrorMessage.USER_PASSWORD_INCORRET);

    }

    @Transactional
    public Page<User> findUser(String word, Pageable pageable) {
        return userRepository.findByNicknameContains(word, pageable);
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
