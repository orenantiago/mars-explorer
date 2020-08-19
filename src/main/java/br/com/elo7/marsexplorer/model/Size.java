package br.com.elo7.marsexplorer.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Size {
    @NotNull
    @Min(1)
    private Integer x;

    @NotNull
    @Min(1)
    private Integer y;
}
