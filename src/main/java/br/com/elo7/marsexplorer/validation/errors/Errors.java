package br.com.elo7.marsexplorer.validation.errors;

import br.com.elo7.marsexplorer.model.Point;
import br.com.elo7.marsexplorer.model.Probe;

public class Errors {

    public static MarsExplorerError ID_REQUIRED = new MarsExplorerError("id required");
    public static MarsExplorerError PROBE_REQUIRED = new MarsExplorerError("probe required");
    public static MarsExplorerError PROBE_NOT_FOUND(Long id) {
        return new MarsExplorerError("probe not found " + id);
    }
    public static MarsExplorerError LAND_NOT_FOUND(Long id) {
        return new MarsExplorerError("land not found " + id);
    }
    public static MarsExplorerError POSITION_OUTSIDE_LAND(Point position) {
        return new MarsExplorerError(String.format("probe put outside land on (%d, %d)", position.getX(), position.getY()));
    }
    public static MarsExplorerError PROBES_COLLIDED(Probe p1, Probe p2, Point position) {
        return new MarsExplorerError(String.format("probes %d and %d collided on (%d, %d) and were removed from land",
                p1.getId(), p2.getId(), position.getX(), position.getY()));
    }

}
