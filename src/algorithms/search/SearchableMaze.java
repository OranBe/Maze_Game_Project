package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchableMaze implements ISearchable , Serializable {
    private Maze maze;
    private AState start;
    private AState goal;

    /**
     * Constructor
     * @param maze
     */
    public SearchableMaze(Maze maze)
    {
        this.maze = maze;
        this.start = new MazeState(maze.getStartPosition());
        this.goal = new MazeState(maze.getGoalPosition());
    }

    /**
     * Get the maze object
     * @return Maze
     */
    public Maze getMaze() {
        return maze;
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
     * @return ArrayList
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        ArrayList<AState> possibleStates = new ArrayList<>();
        int row = ((MazeState) s).getX();
        int col = ((MazeState) s).getY();
        //Top
        if (this.maze.isValidMove(row-1,col)){possibleStates.add(new MazeState(s, 10, row -1, col));
            //Top Right = 15
            if (this.maze.isValidMove(row-1,col + 1)) {possibleStates.add(new MazeState(s, 15, row - 1, col + 1));}
            // Top Left = 15
            if (this.maze.isValidMove(row-1,col - 1)) {possibleStates.add(new MazeState(s, 15, row - 1, col - 1));}
        }
        // Bottom
        if (this.maze.isValidMove(row + 1,col)){possibleStates.add(new MazeState(s, 10, row + 1, col));
            // Bottom Right = 15
            if (this.maze.isValidMove(row + 1,col + 1)){possibleStates.add(new MazeState(s, 15, row + 1, col + 1));}
            // Bottom Left = 15
            if (this.maze.isValidMove(row + 1,col - 1)){possibleStates.add(new MazeState(s, 15, row + 1, col - 1));}
        }
        // Right
        if (this.maze.isValidMove(row,col + 1)){possibleStates.add(new MazeState(s, 10, row, col + 1));
            // Right Top = 15
            if (this.maze.isValidMove(row-1,col + 1)){possibleStates.add(new MazeState(s, 15, row -1, col + 1));}
            // Right Bottom= 15
            if (this.maze.isValidMove(row + 1,col + 1)){possibleStates.add(new MazeState(s, 15, row + 1, col + 1));}
        }
        // Left
        if (this.maze.isValidMove(row,col - 1)){possibleStates.add(new MazeState(s, 10, row , col - 1));
            // Left Bottom = 15
            if (this.maze.isValidMove(row-1,col - 1)) {possibleStates.add(new MazeState(s, 15, row - 1, col - 1));}
            // Left Bottom = 15
            if (this.maze.isValidMove(row + 1,col - 1)){possibleStates.add(new MazeState(s, 15, row + 1, col - 1));}
        }
        return possibleStates;
    }

}
