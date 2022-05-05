package a204.ssayeon.common.model.response;

import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.db.entity.Pagination;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvancedResponseBody<T> extends BaseResponseBody {
    T data;
    Pagination pagination;

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
    public static <T> AdvancedResponseBody<T> of(Status status, T data, Pagination pagination) {
        return (AdvancedResponseBody<T>) AdvancedResponseBody.
                builder().
                status(status).
                pagination(pagination).
                data(data).
                build();
    }

    @Builder
    public AdvancedResponseBody(Status status, T data,Pagination pagination) {
        super(status);
        this.data = data;
        this.pagination = pagination;
    }
}
