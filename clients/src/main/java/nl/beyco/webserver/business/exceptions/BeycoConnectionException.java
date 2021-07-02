package nl.beyco.webserver.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BeycoConnectionException extends RuntimeException {
    public BeycoConnectionException(String message, Throwable e) {
        super(message, e);
    }
}
