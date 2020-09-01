package br.com.elo7.marsexplorer.model;


import io.vavr.Tuple2;

public enum Movement {
    L, R, M;

    public Tuple2<Point, Direction> nextState(Point position, Direction direction) {
        switch (this) {
            case L:
                return new Tuple2(position, direction.left());
            case R:
                return new Tuple2(position, direction.right());
            case M:
                return new Tuple2(position.go(direction), direction);
            default:
                return new Tuple2(position, direction);

        }
    }

}
