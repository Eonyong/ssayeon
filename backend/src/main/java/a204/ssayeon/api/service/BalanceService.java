package a204.ssayeon.api.service;

import a204.ssayeon.api.request.balance.ModifyBalanceReq;
import a204.ssayeon.api.request.balance.RegisterBalanceReq;
import a204.ssayeon.common.exceptions.NotExistException;
import a204.ssayeon.common.model.enums.ErrorMessage;
import a204.ssayeon.db.entity.balance.*;
import a204.ssayeon.db.entity.user.User;
import a204.ssayeon.db.repository.BalanceCommentsLikesRepository;
import a204.ssayeon.db.repository.BalanceCommentsRepository;
import a204.ssayeon.db.repository.BalanceRepository;
import a204.ssayeon.db.repository.BalanceSelectedRepository;
import a204.ssayeon.db.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final UserRepository userRepository;
    private final BalanceCommentsRepository balanceCommentsRepository;
    private final BalanceCommentsLikesRepository balanceCommentsLikesRepository;
    private final BalanceSelectedRepository balanceSelectedRepository;

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

    public Page<Balance> getAllBalance(Pageable pageable) {
        return balanceRepository.findAll(pageable);
    }

    public void modifyBalance(User user,Long balanceId, ModifyBalanceReq req) {
        Balance findBalance = balanceRepository.findByIdAndUser(balanceId,user).orElseThrow(() -> new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));
        findBalance.updateDescription(req.getDescription(),req.getLeftDescription(), req.getRightDescription());
        balanceRepository.save(findBalance);
    }

    public void deleteBalance(User user,Long balanceId) {
        Balance balance = balanceRepository.findByIdAndUser(balanceId,user).orElseThrow(() -> new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));
        balanceRepository.delete(balance);
    }

    public List<BalanceComments> getComments(Balance balance) {
        return balanceCommentsRepository.findByBalance(balance);
    }

    public int getBalanceCommentsLikes(BalanceComments balanceComments) {
        return balanceCommentsLikesRepository.findByBalanceComments(balanceComments).size();
    }

    public void createBalanceComments(User user, Long balanceId, String description) {
        User findUser = userRepository.findById(user.getId()).orElseThrow(() -> new NotExistException(ErrorMessage.USER_DOES_NOT_EXIST));
        Balance findBalance = balanceRepository.findById(balanceId).orElseThrow(() -> new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));
        BalanceComments balanceComments = BalanceComments.builder()
                .user(findUser)
                .balance(findBalance)
                .description(description)
                .build();
        balanceCommentsRepository.save(balanceComments);
    }

    public void registerPoll(User user, Long balanceId, Poll poll) {
        boolean pollNum;
        pollNum = poll == Poll.RIGHT;

        Balance findBalance = balanceRepository.findById(balanceId).orElseThrow(() -> new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));
        User findUser = userRepository.findById(user.getId()).orElseThrow(() -> new NotExistException(ErrorMessage.USER_DOES_NOT_EXIST));
        BalanceSelected balanceSelected;

        if(balanceSelectedRepository.findByBalanceAndUser(findBalance,findUser).isPresent()){
            balanceSelected = balanceSelectedRepository.findByBalanceAndUser(findBalance,findUser).get();
            balanceSelected.updateSelectTarget(pollNum);
            balanceSelectedRepository.save(balanceSelected);
        }else{
            balanceSelected = BalanceSelected.builder()
                    .balance(findBalance)
                    .selectTarget(pollNum)
                    .user(findUser)
                    .build();
        }
        balanceSelectedRepository.save(balanceSelected);
    }

    public int[] getBalanceStatics(Long balanceId) {
        int[] leftRightCount = new int[2];
        int left;
        int right;
        Balance findBalance = balanceRepository.findById(balanceId).orElseThrow(() -> new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));
        List<BalanceSelected> findBalanceSelected = balanceSelectedRepository.findByBalance(findBalance);
        left = (int) findBalanceSelected.stream().filter(BalanceSelected::getSelectTarget).count();
        right = findBalanceSelected.size() - left;
        leftRightCount[0] = right;
        leftRightCount[1] = left;
        return leftRightCount;
    }

    public void updateComments(User user, Long balanceId, Long commentsId, String description) {
        balanceRepository.findById(balanceId).orElseThrow(() -> new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));
        BalanceComments findBalanceComments = balanceCommentsRepository.findByIdAndUser(commentsId, user).orElseThrow(() -> new NotExistException(ErrorMessage.COMMENT_DOES_NOT_EXIST));
        findBalanceComments.updateDescription(description);
        balanceCommentsRepository.save(findBalanceComments);
    }

    public void deleteBalanceComments(User user, Long balanceId, Long commentsId) {
        balanceRepository.findById(balanceId).orElseThrow(() -> new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));
        BalanceComments findComments = balanceCommentsRepository.findByIdAndUser(commentsId, user).orElseThrow(() -> new NotExistException(ErrorMessage.COMMENT_DOES_NOT_EXIST));
        balanceCommentsRepository.delete(findComments);
    }

    public void registerCommentsLikes(User user, Long balanceId, Long commentsId) {
        balanceRepository.findById(balanceId).orElseThrow(() -> new NotExistException(ErrorMessage.ARTICLE_DOES_NOT_EXIST));
        BalanceComments findBalanceComments = balanceCommentsRepository.findById(commentsId).orElseThrow(() -> new NotExistException(ErrorMessage.COMMENT_DOES_NOT_EXIST));
        if(balanceCommentsLikesRepository.findByIdAndUser(commentsId,user).isPresent()){
            BalanceCommentsLikes findBalanceCommentsLikes = balanceCommentsLikesRepository.findByIdAndUser(commentsId, user).get();
            balanceCommentsLikesRepository.delete(findBalanceCommentsLikes);
        }else{
            BalanceCommentsLikes balanceCommentsLikes = BalanceCommentsLikes.builder()
                    .user(user)
                    .balanceComments(findBalanceComments)
                    .build();
            balanceCommentsLikesRepository.save(balanceCommentsLikes);
        }
    }
}
