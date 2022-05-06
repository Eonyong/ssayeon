package a204.ssayeon.common.model.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    //401
    USER_DOES_NOT_EXIST(401,"로그인이 필요한 사용자입니다"),
    USER_EMAIL_INCORRET(401,"이메일 오류"),
    USER_PASSWORD_INCORRET(401,"비밀번호 오류"),
    FORBIDDEN(403,"권한이 없습니다."),
    ARTICLE_DOES_NOT_EXIST(404,"해당 게시글이 존재하지 않습니다."),
    COMMENT_DOES_NOT_EXIST(404,"해당 댓글이 존재하지 않습니다."),

    FORBIDDEN(403,"권한이 없습니다."),
    UNAUTHORIZED(401, "인증 정보가 없습니다"),

    //404
    USER_CERTIFICATION_INCORRECT(404, "인증 오류"),

    //409
    USER_ALREADY_EXIST(409,"이미 존재하는 유저입니다"),
    NICKNAME_ALREADY_EXIST(409,"닉네임 중복"),
    EMAIL_ALREADY_EXIST(409,"이메일 중복"),

    //500
    INTERNAL_SERVER_ERROR(500,"서버 내부 오류")

    ;
    ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private final int status;
    private final String message;

}
