package br.com.elo7.marsexplorer.model;

public enum Direction {
    N, E, S, W;

    public Direction right() {
        return Direction.values()[(this.ordinal() + 1) % Direction.values().length];
    }

    public Direction left() {
        return Direction.values()[(this.ordinal() - 1 + Direction.values().length) % Direction.values().length];
    }
}
