package a204.ssayeon.common.exceptions;

import a204.ssayeon.common.model.enums.ErrorMessage;

public class ForbiddenException extends CustomException {

    public ForbiddenException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
