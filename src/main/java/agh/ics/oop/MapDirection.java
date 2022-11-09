package agh.ics.oop;

public enum MapDirection
{
    NORTH,
    WEST,
    EAST,
    SOUTH,
    UNKNOWN;

    public String toString()
    {
        return switch (this) {
            case NORTH -> "N";
            case EAST -> ">";
            case SOUTH -> "S";
            case WEST -> "<";
            default -> "U";
        };
    }

    public MapDirection next()
    {
        return switch(this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            default -> UNKNOWN;
        };
    }

    public MapDirection previous()
    {
        return switch(this) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
            default -> UNKNOWN;
        };
    }

    public Vector2d toUnitVector()
    {
        return switch(this) {
            case NORTH -> new Vector2d(0,1);
            case EAST -> new Vector2d(1,0);
            case SOUTH -> new Vector2d(0,-1);
            case WEST -> new Vector2d(-1,0);
            default -> new Vector2d(0,0);
        };
    }
}
