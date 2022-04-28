package a204.ssayeon.common.model.response;

import a204.ssayeon.common.model.enums.Status;
import lombok.*;

//메시지 + 데이터를 response 하고 싶을 때
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvancedResponseBody<T> extends BaseResponseBody {
    T data;

    public static <T> AdvancedResponseBody<T> of(Status status, T data) {
        return (AdvancedResponseBody<T>) AdvancedResponseBody.
                builder().
                status(status).
                data(data).
                build();
    }
    public static <T> AdvancedResponseBody<T> of(Status status) {
        return (AdvancedResponseBody<T>) AdvancedResponseBody.
                builder().
                status(status).
                build();
    }

    @Builder
    public AdvancedResponseBody(Status status, T data) {
        super(status);
        this.data = data;
    }
}
