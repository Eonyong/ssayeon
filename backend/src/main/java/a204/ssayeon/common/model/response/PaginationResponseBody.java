package a204.ssayeon.common.model.response;

import a204.ssayeon.common.model.enums.Status;
import a204.ssayeon.db.entity.Pagination;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponseBody<T> extends AdvancedResponseBody<T>{

    private Pagination pagination;

    public static <T> PaginationResponseBody<T> of(Status status, T data,Pagination pagination) {
        return (PaginationResponseBody<T>) PaginationResponseBody.
                builder().
                status(status).
                data(data).
                pagination(pagination).
                build();
    }

    @Builder
    public PaginationResponseBody(Status status, T data, Pagination pagination){
        super(status,data);
        this.pagination = pagination;
    }
}
