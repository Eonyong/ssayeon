package a204.ssayeon.common.exceptions;

import a204.ssayeon.common.model.enums.ErrorMessage;

public class NotExistException extends CustomException{
    public NotExistException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
