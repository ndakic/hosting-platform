package uns.ac.rs.hostplatserver.exception;

import lombok.Getter;
import uns.ac.rs.hostplatserver.constant.ErrorCode;

@Getter
public class ResourceNotExistException extends RuntimeException {

    private final ErrorCode errorCode;

    public ResourceNotExistException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
