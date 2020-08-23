package br.com.elo7.marsexplorer.model;

public enum Direction {
    N, E, S, W;

    public Direction next(Movement movement) {
        switch (movement) {
            case L:
                return Direction.values()[(this.ordinal() - 1) % Direction.values().length];
            case R:
                return Direction.values()[(this.ordinal() + 1) % Direction.values().length];
            default:
                return this;
        }
    }
}
