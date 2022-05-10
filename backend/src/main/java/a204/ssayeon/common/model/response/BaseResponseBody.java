package a204.ssayeon.common.model.response;

import a204.ssayeon.common.model.enums.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;


//메시지만 response 하고 싶을 때
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseBody {

    private int code;

    @Builder(builderMethodName = "baseBuilder")
    public BaseResponseBody(Status status) {
        this.code = status.getCode();
    }
}

