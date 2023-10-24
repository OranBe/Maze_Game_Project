package algorithms.search;

import algorithms.mazeGenerators.Position;
import java.util.Objects;

public class MazeState extends AState{
    private int x;
    private int y;

    /**
     * Constructor
     * This only for start and end position
     * @param pos
     */
    public MazeState(Position pos){
        super(null, 0);
        this.x = pos.getRowIndex();
        this.y = pos.getColumnIndex();
    }

    /**
     * Constructor
     * @param cameFrom
     * @param cost
     * @param x
     * @param y
     */
    public MazeState(AState cameFrom, int cost, int x, int y) {
        super(cameFrom, cost);
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return coordinate X
     */
    public int getX() {return x;}

    /**
     *
     * @return coordinate Y
     */
    public int getY() {return y;}

    /**
     * equals that Override from Object
     * @param other
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (other == null){return false;}
        if (!(other instanceof MazeState)){return false;}
        MazeState otherState = (MazeState) other;
        return otherState.x == this.x && otherState.y == this.y;
    }

    /**
     *  hashCode that Override from Object
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     *
     * @return string
     */
    public String toString() {
        return "{" + x + "," + y + "}";
    }
}