package br.com.elo7.marsexplorer.model;

import br.com.elo7.marsexplorer.exception.errors.Errors;
import br.com.elo7.marsexplorer.exception.errors.MarsExplorerError;
import br.com.elo7.marsexplorer.exception.exceptions.UnprocessableEntityException;
import io.vavr.control.Try;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static io.vavr.control.Try.*;

@Data
@Entity
@Table(name = "lands")
@EqualsAndHashCode(callSuper = true)
public class Land extends BaseEntity implements Serializable {
    @NotNull
    @Valid
    private Point size;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Map<Point, Probe> probes = new HashMap<>();

    public void moveProbes() {
        List<List<Point>> nextPositions = probes.entrySet().stream()
                .filter(entry -> entry.getValue().hasMovements())
                .map(entry -> entry.getValue().moveFrom(entry.getKey()))
                .collect(Collectors.toList());

        List<Throwable> errors = nextPositions.stream().map(points -> move(points))
                .filter(Try::isFailure)
                .map(Try::getCause)
                .collect(Collectors.toList());
        if (!errors.isEmpty()) {
            throw new UnprocessableEntityException().withErrors((List<MarsExplorerError>)(Object) errors);
        }
    }

    public Try move(List<Point> positions) {
        Probe probe = probes.remove(positions.remove(0));

        for(Point position: positions) {
            if (probes.containsKey(position)) {
              return failure(Errors.PROBES_COLLIDED(probe, probes.get(position), position));
            }
            else if (!position.isInside(this)) {
                return failure(Errors.POSITION_OUTSIDE_LAND(position));
            }
        }

        probes.put(positions.get(positions.size()-1), probe);
        return success(null);
    }

    public Set<Point> positions() {
        return probes.keySet();
    }
}
