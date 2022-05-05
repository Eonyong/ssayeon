package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.balance.BalanceCommentsReq;
import a204.ssayeon.api.request.balance.ModifyBalanceReq;
import a204.ssayeon.api.request.balance.RegisterBalanceReq;
import a204.ssayeon.api.request.balance.RegisterPollReq;
import a204.ssayeon.api.response.balance.BalanceCommentsRes;
import a204.ssayeon.api.response.balance.GetAllBalanceRes;
import a204.ssayeon.api.response.balance.GetBalanceRes;
import a204.ssayeon.api.response.balance.GetBalanceStaticsRes;
import a204.ssayeon.api.service.BalanceService;
import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.common.model.response.AdvancedResponseBody;
import a204.ssayeon.config.auth.CurrentUser;
import a204.ssayeon.db.entity.balance.Balance;
import a204.ssayeon.db.entity.balance.BalanceComments;
import a204.ssayeon.db.entity.balance.Poll;
import a204.ssayeon.db.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;

    @PostMapping
    public AdvancedResponseBody<String> registerBalance(@CurrentUser User user, @RequestBody RegisterBalanceReq req){
        balanceService.createBalance(user.getId(),req);
        return AdvancedResponseBody.of(Status.CREATED);
    }

    @GetMapping("/list")
    public AdvancedResponseBody<List<GetAllBalanceRes>> getAllBalance(){
        List<Balance> allBalance = balanceService.getAllBalance();
        List<GetAllBalanceRes> getAllBalanceResList = new ArrayList<>();

        allBalance.forEach((bal)->{
            GetAllBalanceRes res = GetAllBalanceRes.builder()
                    .balanceId(bal.getId())
                    .userId(bal.getUser().getId())
                    .rightDescription(bal.getRightDescription())
                    .description(bal.getDescription())
                    .leftDescription(bal.getLeftDescription())
                    .createdAt(bal.getCreatedAt())
                    .updatedAt(bal.getUpdatedAt())
                    .build();
            getAllBalanceResList.add(res);
        });
        return AdvancedResponseBody.of(Status.OK,getAllBalanceResList);
    }

    @GetMapping("/{balanceId}")
    public AdvancedResponseBody<GetBalanceRes> getBalance(@PathVariable Long balanceId){

        Balance balance = balanceService.getBalance(balanceId);
        List<BalanceComments> comments = balanceService.getComments(balance);
        List<BalanceCommentsRes> commentsRes = new ArrayList<>();

        comments.forEach((comment)->{
            int balanceCommentsLikes = balanceService.getBalanceCommentsLikes(comment);

            BalanceCommentsRes res = BalanceCommentsRes.builder()
                    .BalanceCommentsId(comment.getId())
                    .userId(comment.getUser().getId())
                    .likes(balanceCommentsLikes)
                    .description(comment.getDescription())
                    .createdAt(comment.getCreatedAt())
                    .updatedAt(comment.getUpdatedAt())
                    .build();
            commentsRes.add(res);
        });

        GetBalanceRes getBalanceRes = GetBalanceRes.builder()
                .balanceId(balance.getId())
                .userId(balance.getUser().getId())
                .description(balance.getDescription())
                .leftDescription(balance.getLeftDescription())
                .rightDescription(balance.getRightDescription())
                .createdAt(balance.getCreatedAt())
                .updatedAt(balance.getUpdatedAt())
                .comments(commentsRes)
                .build();

        return AdvancedResponseBody.of(Status.OK,getBalanceRes);
    }

    @PutMapping("/{balanceId}")
    public AdvancedResponseBody<String> modifyBalance(@CurrentUser User user,@PathVariable Long balanceId, @RequestBody ModifyBalanceReq req){
        balanceService.modifyBalance(user,balanceId,req);
        return AdvancedResponseBody.of(Status.OK);
    }

    @DeleteMapping("/{balanceId}")
    public AdvancedResponseBody<String> deleteBalance(@CurrentUser User user,@PathVariable Long balanceId){
        balanceService.deleteBalance(user,balanceId);
        return AdvancedResponseBody.of(Status.NO_CONTENT);
    }

    @PostMapping("/{balanceId}/comments")
    public AdvancedResponseBody<String> registerComments(@CurrentUser User user, @PathVariable Long balanceId, @RequestBody BalanceCommentsReq balanceCommentsReq){
        balanceService.createBalanceComments(user,balanceId,balanceCommentsReq.getDescription());
        return AdvancedResponseBody.of(Status.CREATED);
    }

    @PostMapping("/{balanceId}/poll")
    public AdvancedResponseBody<String> registerPoll(@CurrentUser User user, @PathVariable Long balanceId, @RequestBody RegisterPollReq req){
        Poll poll = req.getPoll();
        balanceService.registerPoll(user,balanceId,poll);
        return AdvancedResponseBody.of(Status.CREATED);
    }

    @GetMapping("/statics/{balanceId}")
    public AdvancedResponseBody<GetBalanceStaticsRes> getBalanceStatics(@PathVariable Long balanceId){
        int[] balanceStatics = balanceService.getBalanceStatics(balanceId);
        int left = balanceStatics[0];
        int right = balanceStatics[1];

        double leftRatio = (double)left/(double)(left+right);
        double rightRatio = (double)right/(double)(left+right);

        GetBalanceStaticsRes getBalanceStaticsRes = GetBalanceStaticsRes.builder()
                .leftRatio(leftRatio)
                .rightRatio(rightRatio)
                .build();
        return AdvancedResponseBody.of(Status.OK,getBalanceStaticsRes);
    }
}
