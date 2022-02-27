package bbpsp.backend.domain.execption;

import bbpsp.backend.global.error.exception.EntityNotFoundException;
import bbpsp.backend.global.error.exception.ErrorCode;

public class NoSuchPlayerException extends EntityNotFoundException {

    public NoSuchPlayerException(String message) {
        super(message);
    }

    public NoSuchPlayerException(ErrorCode errorCode) {
        super(errorCode);
    }
}
