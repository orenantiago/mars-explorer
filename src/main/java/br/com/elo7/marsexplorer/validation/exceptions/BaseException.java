package br.com.elo7.marsexplorer.validation.exceptions;

import br.com.elo7.marsexplorer.validation.MarsExplorerError;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class BaseException extends RuntimeException {
    @JsonProperty
    private HttpStatus status;

    @JsonProperty
    private List<MarsExplorerError> errors;

    public BaseException(HttpStatus status) {
        this.status = status;
    }

    public BaseException withErrors(List<MarsExplorerError> errors) {
        this.errors = errors;
        return this;
    }

    public BaseException withErrors(MarsExplorerError... errors) {
        this.errors = Arrays.stream(errors).collect(Collectors.toList());
        return this;
    }
}
