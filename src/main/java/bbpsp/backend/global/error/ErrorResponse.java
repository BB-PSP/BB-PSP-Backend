package bbpsp.backend.global.error;

import bbpsp.backend.global.error.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String message;
    private int status;
    private String code;

    private ErrorResponse(final ErrorCode code) {
        this.message = code.message();
        this.status = code.status();
        this.code = code.code();
    }

    public static ErrorResponse of(ErrorCode commonErrorCode) {
        return new ErrorResponse(commonErrorCode);
    }
}
