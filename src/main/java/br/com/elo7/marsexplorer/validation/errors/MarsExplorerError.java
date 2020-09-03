package br.com.elo7.marsexplorer.validation.errors;

import com.fasterxml.jackson.annotation.JsonValue;

public class MarsExplorerError extends Error {
    public MarsExplorerError(String message) {
        super(message);
    }

    @JsonValue
    public String toString() {
        return getMessage();
    }
}
