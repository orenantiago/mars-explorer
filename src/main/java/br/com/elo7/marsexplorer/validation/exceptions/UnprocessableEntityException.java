package br.com.elo7.marsexplorer.validation.exceptions;

import br.com.elo7.marsexplorer.validation.MarsExplorerError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends BaseException {
}
