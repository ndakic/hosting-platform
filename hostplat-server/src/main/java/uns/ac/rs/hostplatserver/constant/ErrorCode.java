package uns.ac.rs.hostplatserver.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
    MISSING_TOKEN(HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED),
    RESOURCE_ALREADY_EXISTS(HttpStatus.NOT_ACCEPTABLE),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    UNKNOWN,
    ILLEGAL_ARGUMENT;

    private final HttpStatus httpStatus;

    ErrorCode() {
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
