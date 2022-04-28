package a204.ssayeon.common.model.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    //401
    USER_DOES_NOT_EXIST(401,""),



    //409
    USER_ALREADY_EXIST(409,"이미 존재하는 유저입니다"),
    NICKNAME_ALREADY_EXIST(409,"닉네임 중복"),
    EMAIL_ALREADY_EXIST(409,"이메일 중복"),

    ;
    ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private final int status;
    private final String message;

}
