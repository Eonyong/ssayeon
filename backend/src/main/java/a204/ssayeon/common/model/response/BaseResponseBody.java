package a204.ssayeon.common.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//메시지만 response 하고 싶을 때
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseBody {
    String result = null;

    public static BaseResponseBody of(String result) {
        BaseResponseBody body = new BaseResponseBody();
        body.result = result;
        return body;
    }
}

