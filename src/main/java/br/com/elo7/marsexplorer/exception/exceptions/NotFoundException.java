package br.com.elo7.marsexplorer.exception.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
