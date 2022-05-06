package a204.ssayeon.common.exceptions;

import a204.ssayeon.common.model.enums.ErrorMessage;

public class InternalServerException extends CustomException{
    public InternalServerException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
