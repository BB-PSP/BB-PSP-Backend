package bbpsp.backend.domain.execption;

import bbpsp.backend.global.error.exception.EntityNotFoundException;
import bbpsp.backend.global.error.exception.ErrorCode;

public class NoSuchBatterStatException extends EntityNotFoundException {
    public NoSuchBatterStatException(String message) {
        super(message);
    }

    public NoSuchBatterStatException(ErrorCode errorCode) {
        super(errorCode);
    }
}
