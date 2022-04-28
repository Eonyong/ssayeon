package a204.ssayeon.api.controller;

import a204.ssayeon.api.request.RegisterBalanceReq;
import a204.ssayeon.api.response.GetBalanceRes;
import a204.ssayeon.api.service.BalanceService;
import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.common.model.response.AdvancedResponseBody;
import a204.ssayeon.db.entity.balance.Balance;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;

    @PostMapping
    public AdvancedResponseBody<String> registerBalance(@RequestBody RegisterBalanceReq req){
        Long userId = 3L;

        balanceService.createBalance(userId,req);
        return AdvancedResponseBody.of(Status.OK);
    }

    @GetMapping("/list")
    public AdvancedResponseBody<String> getAllBalance(){
        Long userId = 3L;

        return AdvancedResponseBody.of(Status.OK);
    }

    @GetMapping("/{balanceId}")
    public AdvancedResponseBody<GetBalanceRes> getBalance(@PathVariable Long balanceId){

        Balance balance = balanceService.getBalance(balanceId);

        GetBalanceRes getBalanceRes = GetBalanceRes.builder()
                .balanceId(balance.getId())
                .userId(balance.getUser().getId())
                .description(balance.getDescription())
                .leftDescription(balance.getLeftDescription())
                .rightDescription(balance.getRightDescription())
                .createdAt(balance.getCreatedAt())
                .updatedAt(balance.getUpdatedAt())
                .build();

        return AdvancedResponseBody.of(Status.OK,getBalanceRes);
    }
}
