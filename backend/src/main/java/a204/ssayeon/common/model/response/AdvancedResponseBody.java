package a204.ssayeon.common.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//메시지 + 데이터를 response 하고 싶을 때
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvancedResponseBody<T> extends BaseResponseBody {
    T data;

    public AdvancedResponseBody(int code, String message, T data) {
        super(code, message);
        this.data=data;
    }

}
