package a204.ssayeon.api.service;

import a204.ssayeon.api.request.RegisterBalanceReq;
import a204.ssayeon.common.exceptions.NotExistException;
import a204.ssayeon.common.model.enums.ErrorMessage;
import a204.ssayeon.db.entity.balance.Balance;
import a204.ssayeon.db.entity.user.User;
import a204.ssayeon.db.repository.BalanceRepository;
import a204.ssayeon.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final UserRepository userRepository;

    public void createBalance(Long userId, RegisterBalanceReq req) {
        String description = req.getDescription();
        String leftDescription = req.getLeftDescription();
        String rightDescription = req.getRightDescription();

        User user = userRepository.findById(userId).orElseThrow(() -> new NotExistException(ErrorMessage.USER_DOES_NOT_EXIST));

        Balance balance = Balance.builder()
                .user(user)
                .description(description)
                .leftDescription(leftDescription)
                .rightDescription(rightDescription)
                .build();


        balanceRepository.save(balance);
    }

    public Balance getBalance(Long balanceId) {
        return balanceRepository.findById(balanceId).orElseThrow(()->new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));
    }
}
