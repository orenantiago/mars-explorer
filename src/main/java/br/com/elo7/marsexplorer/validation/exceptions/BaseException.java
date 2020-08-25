package br.com.elo7.marsexplorer.validation.exceptions;

import br.com.elo7.marsexplorer.validation.MarsExplorerError;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BaseException extends RuntimeException {
    private List<MarsExplorerError> errors;

    public BaseException withErrors(List<MarsExplorerError> errors) {
        this.errors = errors;
        return this;
    }

    public BaseException withErrors(MarsExplorerError... errors) {
        this.errors = Arrays.stream(errors).collect(Collectors.toList());
        return this;
    }

}
