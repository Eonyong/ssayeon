package a204.ssayeon.common.model.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    USER_DOES_NOT_EXIST(401,""),

    ;
    ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private final int status;
    private final String message;

}
