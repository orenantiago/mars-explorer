package br.com.elo7.marsexplorer.model;

public enum Direction {
    N, E, S, W;

    public Direction right() {
        return Direction.values()[(this.ordinal() + 1) % Direction.values().length];
    }

    public Direction left() {
        return Direction.values()[(this.ordinal() - 1 + Direction.values().length) % Direction.values().length];
    }

    public Position move(Position position) {
        switch (this) {
            case N:
                return Position.at(position.getX(), position.getY() + 1);
            case E:
                return Position.at(position.getX() + 1, position.getY());
            case S:
                return Position.at(position.getX(), position.getY() - 1);
            case W:
                return Position.at(position.getX() - 1, position.getY());
            default:
                return position;
        }
    }
}
