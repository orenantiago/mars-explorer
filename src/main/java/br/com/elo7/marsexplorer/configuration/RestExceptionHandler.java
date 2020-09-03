package br.com.elo7.marsexplorer.configuration;

import br.com.elo7.marsexplorer.exception.exceptions.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<BaseException> handle(BaseException ex) {
        return new ResponseEntity<BaseException>(ex, ex.getStatus());
    }

}
