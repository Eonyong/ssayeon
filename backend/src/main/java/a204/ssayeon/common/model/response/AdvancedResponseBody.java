package a204.ssayeon.common.model.response;

import lombok.*;

//메시지 + 데이터를 response 하고 싶을 때
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvancedResponseBody<T> extends BaseResponseBody {
    T data;

    public static <T> AdvancedResponseBody of(String result, T data) {
        return AdvancedResponseBody.builder().result(result).data(data).build();
    }

    @Builder
    public AdvancedResponseBody(String result, T data) {
        super(result);
        this.data = data;
    }
}
