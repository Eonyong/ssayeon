package a204.ssayeon.api.request.balance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBalanceReq {
    private String description;
    private String leftDescription;
    private String rightDescription;
}
