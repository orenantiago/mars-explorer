package br.com.elo7.marsexplorer.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Embeddable
@EqualsAndHashCode
public class Position {
    @NotNull
    @Min(1)
    private Integer x;

    @NotNull
    @Min(1)
    private Integer y;

}
