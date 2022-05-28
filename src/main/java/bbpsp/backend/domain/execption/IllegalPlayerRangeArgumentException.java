package bbpsp.backend.domain.execption;

import bbpsp.backend.global.error.exception.BusinessException;
import bbpsp.backend.global.error.exception.ErrorCode;

public class IllegalPlayerRangeArgumentException extends BusinessException {
    public IllegalPlayerRangeArgumentException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public IllegalPlayerRangeArgumentException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}
