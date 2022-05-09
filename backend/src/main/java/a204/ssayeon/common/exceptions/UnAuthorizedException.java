package a204.ssayeon.common.exceptions;

import a204.ssayeon.common.model.enums.ErrorMessage;

public class UnAuthorizedException extends CustomException {

    public UnAuthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
