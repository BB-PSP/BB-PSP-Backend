package bbpsp.backend.domain.execption;

import bbpsp.backend.global.error.exception.BusinessException;
import bbpsp.backend.global.error.exception.ErrorCode;

public class IllegalPositionSelectException extends BusinessException {
    public IllegalPositionSelectException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public IllegalPositionSelectException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}
