package br.com.elo7.marsexplorer.exception.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }

}
