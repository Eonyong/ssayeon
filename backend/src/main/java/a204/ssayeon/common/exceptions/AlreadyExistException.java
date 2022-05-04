package a204.ssayeon.common.exceptions;

import a204.ssayeon.common.model.enums.ErrorMessage;

public class AlreadyExistException extends CustomException{
    public AlreadyExistException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
