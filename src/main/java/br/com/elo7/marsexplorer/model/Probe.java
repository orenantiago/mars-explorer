package br.com.elo7.marsexplorer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.javatuples.Pair;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true, of={"id"})
@Entity
@Table(name = "probes")
public class Probe extends BaseEntity implements Serializable {
    @NotNull
    private Direction direction;

    @Transient
    private List<Movement> movements;

    public Boolean hasId() {
        return getId() != null;
    }

    public List<Position> move(Position position) {
        return movements.stream().map(movement -> {
            Pair<Position, Direction> state = movement.nextState(position, direction);
            this.direction = state.getValue1();
            return state.getValue0();
        }).collect(Collectors.toList());
    }
}
