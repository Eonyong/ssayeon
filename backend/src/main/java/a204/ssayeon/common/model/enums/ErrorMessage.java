package a204.ssayeon.common.model.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    USER_DOES_NOT_EXIST(401,""),
    ARTICLE_DOES_NOT_EXIST(401,"해당 게시글이 존재하지 않습니다.")

    ;
    ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private final int status;
    private final String message;

}
