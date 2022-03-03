package bbpsp.backend.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "잘못된 입력입니다."),
    METHOD_NOT_ALLOWED(405, "C002", "잘못된 요청입니다."),
    ENTITY_NOT_FOUND(400, "C003", "개체를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "C004", "서버 내부 오류 입니다."),
    INVALID_TYPE_VALUE(400, "C005", "잘못된 타입 입니다."),
    HANDLE_ACCESS_DENIED(403, "C006", "잘못된 접근 입니다."),

    // Season
    NO_SUCH_SEASON(404, "S001", "존재하지 않는 시즌입니다."),

    // Team
    NO_SUCH_TEAM(404, "T001", "존재하지 않는 팀입니다."),
    // Player
    NO_SUCH_PLAYER(404, "P001", "존재하지 않는 선수입니다."),

    // BatterStat
    NO_SUCH_BATTER_STAT(404, "BS001", "해당 선수의 타자 기록이 존재하지 않습니다."),

    // PitcherStat
    NO_SUCH_PITCHER_STAT(404, "PS001", "해당 선수의 타자 기록이 존재하지 않습니다.")

    ;

    // Mate domain

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }


    public int status() {
        return status;
    }

    public String message() {
        return message;
    }

    public String code() {
        return code;
    }

}
