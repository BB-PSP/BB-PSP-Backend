package bbpsp.backend.domain.execption;

import bbpsp.backend.global.error.exception.EntityNotFoundException;
import bbpsp.backend.global.error.exception.ErrorCode;

public class NoSuchSeasonException extends EntityNotFoundException {
    public NoSuchSeasonException(String message) {
        super(message);
    }

    public NoSuchSeasonException(ErrorCode errorCode) {
        super(errorCode);
    }
}
