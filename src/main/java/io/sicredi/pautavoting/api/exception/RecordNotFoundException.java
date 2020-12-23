package io.sicredi.pautavoting.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 7623146791537233470L;

    public RecordNotFoundException(String message) {
        super(message);
    }
}