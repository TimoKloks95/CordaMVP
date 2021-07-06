package nl.beyco.webserver.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BeycoParseException extends RuntimeException {
    public BeycoParseException(String message, Throwable e) {
        super(message, e);
    }
}
