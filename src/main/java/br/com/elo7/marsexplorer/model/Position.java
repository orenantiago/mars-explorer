package br.com.elo7.marsexplorer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Embeddable
@Data
@EqualsAndHashCode(of = {"x", "y"})
public class Position {
    @NotNull
    @Min(1)
    private Integer x;

    @NotNull
    @Min(1)
    private Integer y;

    public Position() {}
    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public static Position at(Integer x, Integer y) {
        return new Position(x, y);
    }

    public boolean isInside(Land that) {
        return this.getX() <= that.getSize().getX() && this.getY() <= that.getSize().getY();
    }
}
