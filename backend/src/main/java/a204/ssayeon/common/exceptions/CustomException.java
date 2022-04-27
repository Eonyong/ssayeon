package a204.ssayeon.common.exceptions;

import a204.ssayeon.common.model.enums.ErrorMessage;

public class CustomException extends RuntimeException{
    private final ErrorMessage errorMessage;

    public CustomException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
    public ErrorMessage getErrorMessage(){return errorMessage;}
}