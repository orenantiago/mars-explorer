package br.com.elo7.marsexplorer.model;

import io.vavr.Tuple2;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "probes")
@EqualsAndHashCode(callSuper = true)
public class Probe extends BaseEntity implements Serializable {
    @NotNull
    private Direction direction;

    @ElementCollection(targetClass = Movement.class)
    private List<Movement> movements = new ArrayList<>();

    public Boolean hasId() {
        return getId() != null;
    }

    public Boolean hasMovements() {
        return movements != null && !movements.isEmpty();
    }

    public List<Point> moveFrom(Point position) {
        Point tmpPosition = position;
        List<Point> nextPositions = new ArrayList<>();
        nextPositions.add(position);
        for (Movement movement : movements) {
            Tuple2<Point, Direction> nextState = movement.nextState(tmpPosition, direction);
            if (nextState._2 != direction) {
                direction = nextState._2;
            }
            if (nextState._1 != tmpPosition) {
                tmpPosition = nextState._1;
                nextPositions.add(tmpPosition);
            }
        }
        return nextPositions;
    }
}
