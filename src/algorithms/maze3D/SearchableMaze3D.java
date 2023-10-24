package algorithms.maze3D;


import algorithms.search.AState;
import algorithms.search.ISearchable;


import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private final Maze3D maze3D;
    private final AState start;
    private final AState goal;

    /**
     * Constructor
     * @param maze3D
     */
    public SearchableMaze3D(Maze3D maze3D) {
        this.maze3D = maze3D;
        this.start = new Maze3DState(maze3D.getStartPosition());
        this.goal = new Maze3DState(maze3D.getGoalPosition());
    }

    /**
     * Get start position {state}
     * @return AState
     */
    @Override
    public AState getStartState() {return this.start;}

    /**
     * Get goal position {state}
     * @return AState
     */
    @Override
    public AState getGoalState() {return this.goal;}

    /**
     * Check all the eight neighbors and if the current node can walk to this position,
     * add this position to the ArrayList.
     * @param s - the current node in the algorithm
     * @return ArrayList AState
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        ArrayList<AState> possibleStates = new ArrayList<>();
        int depth = ((Maze3DState) s).getX();
        int row = ((Maze3DState) s).getY();
        int col = ((Maze3DState) s).getZ();

        /////////////
        //Regular steps
        Position3D up = new Position3D(depth+1,row, col);
        Position3D down = new Position3D(depth-1, row, col);

        Position3D forward = new Position3D(depth,row + 1, col);
        Position3D backwards = new Position3D(depth,row - 1, col);
        Position3D right = new Position3D(depth, row, col + 1);
        Position3D left = new Position3D(depth, row, col - 1);

        if (maze3D.isValidMove(depth+1,row, col)) { possibleStates.add(new Maze3DState(up)); }
        if (maze3D.isValidMove(depth-1, row, col)) { possibleStates.add(new Maze3DState(down)); }

        if (maze3D.isValidMove(depth,row + 1, col)) { possibleStates.add(new Maze3DState(forward)); }
        if (maze3D.isValidMove(depth,row - 1, col)) { possibleStates.add(new Maze3DState(backwards)); }
        if (maze3D.isValidMove(depth, row, col + 1)) { possibleStates.add(new Maze3DState(right)); }
        if (maze3D.isValidMove(depth, row, col - 1)) { possibleStates.add(new Maze3DState(left)); }
        /////////////

        return possibleStates;
    }

}
