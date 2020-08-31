package br.com.elo7.marsexplorer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Embeddable
@Data
@EqualsAndHashCode(of = {"x", "y"})
public class Point {
    @NotNull
    @Min(1)
    private Integer x;

    @NotNull
    @Min(1)
    private Integer y;

    public Point() {}
    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public static Point at(Integer x, Integer y) {
        return new Point(x, y);
    }

    public Point go(Direction direction) {
        switch (direction) {
            case N:
                return Point.at(x, y + 1);
            case E:
                return Point.at(x + 1, y);
            case S:
                return Point.at(x, y - 1);
            case W:
                return Point.at(x - 1, y);
            default:
                return this;
        }
    }

    public boolean isInside(Land land) {
        return this.getX() <= land.getSize().getX() && this.getY() <= land.getSize().getY();
    }
}
