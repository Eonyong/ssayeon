package a204.ssayeon.common.exceptions.handler;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final HttpStatus code;
    private final int status;
    private final String message;
}
