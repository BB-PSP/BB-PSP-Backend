package bbpsp.backend.domain.execption;

import bbpsp.backend.global.error.exception.EntityNotFoundException;
import bbpsp.backend.global.error.exception.ErrorCode;

public class NoSuchPitcherStatException extends EntityNotFoundException {
    public NoSuchPitcherStatException(String message) {
        super(message);
    }

    public NoSuchPitcherStatException(ErrorCode errorCode) {
        super(errorCode);
    }
}
