package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.MazeState;

import java.util.Objects;

public class Maze3DState extends AState {
    private final int x;
    private final int y;
    private final int z;

    /**
     * Constructor
     * @param pos
     */
    public Maze3DState(Position3D pos){
        super(null, 1);
        this.x = pos.getDepthIndex();
        this.y = pos.getRowIndex();
        this.z = pos.getColumnIndex();
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
     *
     * @return coordinate Z
     */
    public int getZ() {return z;}

    /**
     * equals that Override from Object
     * @param other
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (other == null){return false;}
        if (!(other instanceof Maze3DState)){return false;}
        Maze3DState otherState = (Maze3DState) other;
        return otherState.x == this.x && otherState.y == this.y && otherState.z == this.z;
    }

    /**
     *  hashCode that Override from Object
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y , z);
    }

    /**
     *
     * @return string
     */
    @Override
    public String toString() {
        return "{" + x + "," + y + "," + z + "}";    }

}
