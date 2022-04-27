package a204.ssayeon.api.controller;

import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.common.model.response.AdvancedResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contents")
public class BalanceController {

    @PostMapping
    public AdvancedResponseBody<String> registerBalance(){
        return AdvancedResponseBody.of(Status.OK);
    }
}
