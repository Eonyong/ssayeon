package a204.ssayeon.api.service;

import a204.ssayeon.db.entity.user.Alarm;
import a204.ssayeon.db.entity.user.User;
import a204.ssayeon.db.repository.user.AlarmRepository;
import a204.ssayeon.db.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {

    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;

    @Transactional
    public void deleteUser(User user){
        userRepository.delete(user);
    }
    @Transactional(readOnly=true)
    public Page<List<Alarm>> showAlarm(User user, Pageable pageable){
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());

        return alarmRepository.findByUser(user, pageRequest);
    }

    @Transactional
    public void sendAlarm(User user){
        user.setIsAlarm();
        userRepository.save(user);
    }

    @Transactional
    public void readAlarm(Long alarmId){
        alarmRepository.updateRead(alarmId);
    }
}
