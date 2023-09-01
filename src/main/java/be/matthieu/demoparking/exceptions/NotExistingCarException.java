package be.matthieu.demoparking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class NotExistingCarException extends RuntimeException{
    public NotExistingCarException() {
        super("Car not present in parking");
    }
}
