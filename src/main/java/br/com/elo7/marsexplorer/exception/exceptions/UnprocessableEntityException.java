package br.com.elo7.marsexplorer.exception.exceptions;

import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends BaseException {
    public UnprocessableEntityException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
