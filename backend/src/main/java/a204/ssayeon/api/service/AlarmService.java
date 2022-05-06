package a204.ssayeon.api.service;

import a204.ssayeon.common.exceptions.InternalServerException;
import a204.ssayeon.common.exceptions.NotExistException;
import a204.ssayeon.common.model.enums.ErrorMessage;
import a204.ssayeon.db.entity.balance.Balance;
import a204.ssayeon.db.entity.user.Alarm;
import a204.ssayeon.db.entity.user.User;
import a204.ssayeon.db.repository.BalanceRepository;
import a204.ssayeon.db.repository.user.AlarmRepository;
import a204.ssayeon.db.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlarmService {

    private final String url ="k6a204.p.ssafy.io";
    private final BalanceRepository balanceRepository;
    private final AlarmRepository alarmRepository;

    public void sendBalanceWritingAlarm(Long balanceId){
        Balance balance = balanceRepository.findById(balanceId).orElseThrow(() -> new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR));
        User user = balance.getUser();
        Alarm alarm = Alarm.builder()
                .user(user)
                .url(url+ "/balance/" +balanceId)
                .description(user.getNickname() + "님이 쓴 게시글에 댓글이 달렸습니다.")
                .build();
        alarmRepository.save(alarm);
    }
}
