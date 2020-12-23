package io.sicredi.pautavoting.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DuplicatedIDException extends RuntimeException {
    private static final long serialVersionUID = -5519773214194025639L;

    public DuplicatedIDException(String message) {
        super(message);
    }
}