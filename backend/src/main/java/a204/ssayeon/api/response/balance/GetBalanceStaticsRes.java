package a204.ssayeon.api.response.balance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetBalanceStaticsRes {
    private double leftRatio;
    private double rightRatio;
}
