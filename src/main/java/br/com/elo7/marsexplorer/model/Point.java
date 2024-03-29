package br.com.elo7.marsexplorer.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@Embeddable
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

    public Boolean isInside(Land land) {
        return isBetween(x, 0, land.getSize().getX()) && isBetween(y, 0, land.getSize().getY());
    }

    private Boolean isBetween(Integer i, Integer min, Integer max) {
        return i <= max && i >= min;
    }

    public Point(String p) {
        String[] split = p.replaceAll("[()\\ ]", "").split(",");
        x = Integer.valueOf(split[0]);
        y = Integer.valueOf(split[1]);
    }

    @JsonValue
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }
}
