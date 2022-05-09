package a204.ssayeon.common.model.response;

import a204.ssayeon.common.model.enums.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvancedResponseBody<T> extends BaseResponseBody {
    T data;

    public static <T> AdvancedResponseBody<T> of(Status status, T data) {
        return (AdvancedResponseBody<T>) AdvancedResponseBody.
                advancedBuilder().
                status(status).
                data(data).
                build();
    }
    public static <T> AdvancedResponseBody<T> of(Status status) {
        return (AdvancedResponseBody<T>) AdvancedResponseBody.
                advancedBuilder().
                status(status).
                build();
    }

    @Builder(builderMethodName = "advancedBuilder")
    public AdvancedResponseBody(Status status, T data) {
        super(status);
        this.data = data;
    }
}
