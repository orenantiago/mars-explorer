package br.com.elo7.marsexplorer.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MarsExplorerValidator {
    private Validator validator;

    public MarsExplorerValidator() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public<T> List<MarsExplorerError> validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        return violations.stream()
                .map(violation -> getError(violation))
                .collect(Collectors.toList());
    }

    private MarsExplorerError getError (ConstraintViolation violation) {
        return new MarsExplorerError(String.format("%s %s", violation.getPropertyPath().toString(), violation.getMessage()));
    }
}
