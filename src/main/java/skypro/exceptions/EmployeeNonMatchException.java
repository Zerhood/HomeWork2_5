package skypro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmployeeNonMatchException extends RuntimeException {
    public EmployeeNonMatchException(String message) {
        super(message);
    }
}