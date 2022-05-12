package a204.ssayeon.api.service;

import a204.ssayeon.api.request.user.UserEditPasswordReq;
import a204.ssayeon.api.request.user.UserEditUserReq;
import a204.ssayeon.api.response.article.BoardRes;
import a204.ssayeon.api.response.article.CategoryRes;
import a204.ssayeon.api.response.user.UserShowMyPageRes;
import a204.ssayeon.api.response.user.UserShowUserActivityRes;
import a204.ssayeon.api.response.user.UserShowUserRes;
import a204.ssayeon.api.response.user.UserUnreadMessageCntRes;
import a204.ssayeon.common.exceptions.NotExistException;
import a204.ssayeon.common.exceptions.NotJoinedUserException;
import a204.ssayeon.common.model.enums.ErrorMessage;
import a204.ssayeon.config.aws.S3Util;
import a204.ssayeon.db.entity.Pagination;
import a204.ssayeon.db.entity.article.Article;
import a204.ssayeon.db.entity.article.Board;
import a204.ssayeon.db.entity.article.Category;
import a204.ssayeon.db.entity.user.*;
import a204.ssayeon.db.repository.article.ArticleRepository;
import a204.ssayeon.db.repository.article.BoardRepository;
import a204.ssayeon.db.repository.article.CategoryRepository;
import a204.ssayeon.db.repository.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserService {

    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TechStackRepository techStackRepository;
    private final UserHasTechStackRepository userHasTechStackRepository;
    private final S3Util s3util;
    private final MessageRepository messageRepository;
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public UserShowUserRes showUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotExistException(ErrorMessage.USER_DOES_NOT_EXIST));

        return UserShowUserRes.builder().id(user.getId()).company(user.getCompany()).nickname(user.getNickname()).picture(user.getPicture())
                .techStacks(userHasTechStackRepository.findByUser(user).stream().map(userHasTechStack -> userHasTechStack.getTechStack().getDescription()).collect(Collectors.toList())).build();

    }

    @Transactional(readOnly = true)
    public UserShowMyPageRes showMyPage(User user) {
        return UserShowMyPageRes.builder().id(user.getId()).name(user.getName()).classId(user.getClassId()).email(user.getEmail()).company(user.getCompany()).nickname(user.getNickname()).picture(user.getPicture())
                .techStacks(userHasTechStackRepository.findByUser(user).stream().map(userHasTechStack -> userHasTechStack.getTechStack().getDescription()).collect(Collectors.toList())).build();

    }

    @Transactional(readOnly = true)
    public Object[] showUserActivity(Long id, Pageable pageable) {
        Page<Article> articlePage = articleRepository.findByUserId(id, pageable);
        Pagination pagination = Pagination.getPagination(articlePage); //페이지네이션

        List<UserShowUserActivityRes> articleList = new ArrayList<>();

        articlePage.forEach((article) -> {
            Board board = article.getBoard();
            Category category = article.getCategory();
            articleList.add(UserShowUserActivityRes.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .views(article.getViews())
                    .board(BoardRes.builder()
                            .id(board.getId())
                            .name(board.getName())
                            .build())
                    .category(CategoryRes.builder().id(category.getId()).name(category.getName()).build())
                    .build());
        });

        return new Object[]{pagination, articleList};
    }

    @Transactional(readOnly = true)
    public UserUnreadMessageCntRes unreadMessageCnt(User user) {
        return UserUnreadMessageCntRes.builder().unreadMessageCnt(messageRepository.findByReceiverAndIsReadIsFalseCnt(user)).build();
    }


    @Transactional(readOnly = true)
    public Page<Message> showMessageList(User user, Pageable pageable) {
        return messageRepository.findByMessageListSenderOrReceiver(user.getId(), user.getId(), pageable);
    }

    @Transactional
    public Page<Message> showMessageDetail(User user, Long otherUserId, Pageable pageable) {
        messageRepository.updateByReceiverIdAndSenderIdAndIsReadIsFalseToTrue(user.getId(), otherUserId); //읽음 체크
        return messageRepository.findByIdAndSenderOrReceiver(user.getId(), otherUserId, pageable);
    }

    @Transactional
    public void sendMessage(User user, Long toUserId, String description) {
        User receiver = userRepository.findById(toUserId).orElseThrow(() -> new NotJoinedUserException(ErrorMessage.USER_DOES_NOT_EXIST));
        messageRepository.save(Message.builder().description(description).sender(user).receiver(receiver).build());
    }

    @Transactional
    public void editUser(User user, UserEditUserReq userEditUserReq) throws IOException {
        if (userEditUserReq.getNickname() != null)
            user.setNickname(userEditUserReq.getNickname());
        if (userEditUserReq.getPicture()!=null) {
            if (user.getPicture() != null) {
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

    @Transactional(readOnly = true)
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
