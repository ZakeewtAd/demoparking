package be.matthieu.demoparking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotExistingParkingException extends RuntimeException {
    public NotExistingParkingException() {
        super("The requested parking does not exist");
    }
}
