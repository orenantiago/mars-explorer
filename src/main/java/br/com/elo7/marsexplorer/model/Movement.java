package br.com.elo7.marsexplorer.model;


import org.javatuples.Pair;

public enum Movement {
    L, R, M;

    public Pair<Point, Direction> nextState(Point position, Direction direction) {
        switch (this) {
            case L:
                return Pair.with(position, direction.left());
            case R:
                return Pair.with(position, direction.right());
            case M:
                return Pair.with(position.go(direction), direction);
            default:
                return Pair.with(position, direction);

        }
    }

}
