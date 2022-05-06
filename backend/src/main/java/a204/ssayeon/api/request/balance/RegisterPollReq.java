package a204.ssayeon.api.request.balance;

import a204.ssayeon.db.entity.balance.Poll;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPollReq {
    private Poll poll;
}
