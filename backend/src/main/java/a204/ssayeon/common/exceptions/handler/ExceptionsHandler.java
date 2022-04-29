package a204.ssayeon.common.exceptions.handler;

import a204.ssayeon.common.exceptions.AlreadyExistException;
import a204.ssayeon.common.exceptions.CustomException;
import a204.ssayeon.common.exceptions.NotExistException;
import a204.ssayeon.common.exceptions.NotJoinedUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ExceptionsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotExistException.class)
    public ErrorResponse notFound(CustomException e){
        return ErrorResponse.builder()
                .message(e.getErrorMessage().getMessage())
                .status(e.getErrorMessage().getStatus())
                .build();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotJoinedUserException.class)
    public ErrorResponse notJoinedUser(CustomException e){
        return ErrorResponse.builder()
                .code(HttpStatus.UNAUTHORIZED)
                .message(e.getErrorMessage().getMessage())
                .status(e.getErrorMessage().getStatus())
                .build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AlreadyExistException.class)
    public ErrorResponse alreadyExist(CustomException e){
        return ErrorResponse.builder()
                .code(HttpStatus.CONFLICT)
                .message(e.getErrorMessage().getMessage())
                .status(e.getErrorMessage().getStatus())
                .build();
    }
}
