package be.matthieu.demoparking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaxCapacityReachException extends RuntimeException {
    public MaxCapacityReachException(String parkingName) {
        super("Max capacity reached in " + parkingName);
    }
}
