package bbpsp.backend.domain.execption;

import bbpsp.backend.global.error.exception.EntityNotFoundException;
import bbpsp.backend.global.error.exception.ErrorCode;

public class NoSuchTeamException extends EntityNotFoundException {
    public NoSuchTeamException(String message) {
        super(message);
    }

    public NoSuchTeamException(ErrorCode errorCode) {
        super(errorCode);
    }
}
