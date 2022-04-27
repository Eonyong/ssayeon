package a204.ssayeon.common.exceptions.handler;

import a204.ssayeon.common.exceptions.CustomException;
import a204.ssayeon.common.exceptions.NotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
@ResponseBody
public class ExceptionsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotExistException.class)
    public ErrorResponse notFound(CustomException e){
        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST)
                .message(e.getErrorMessage().getMessage())
                .status(e.getErrorMessage().getStatus())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
