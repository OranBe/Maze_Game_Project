package algorithms.mazeGenerators;

import java.io.Serializable;

public class Maze implements Serializable {
    private int [][] mazeArray;
    private  int rows;
    private  int columns;
    private Position StartPosition;
    private Position GoalPosition;

    /**
     * Constructor
     * @param rows
     * @param columns
     */
    public Maze(int rows, int columns) {
        if (rows >= 2 || columns >= 2){
            this.rows = rows;
            this.columns = columns;
            this.mazeArray = new int[rows][columns];
        }
    }

    /**
     * Constructor
     * @param byteArr array
     */
    public Maze(byte []byteArr){
        this.rows = byteArr[0]*127 + byteArr[1];
        this.columns = byteArr[2]*127 + byteArr[3];
        this.mazeArray = new int[this.rows][this.columns];

        Position start = new Position(byteArr[4]*127 + byteArr[5],byteArr[6]*127 + byteArr[7] );
        Position goal = new Position(byteArr[8]*127 + byteArr[9],byteArr[10]*127 + byteArr[11] );

        setStart(start);
        setEnd(goal);

        int index = 12;
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                setValueToCell(row, column, byteArr[index]);
                index++;
            }
        }
    }

    /**
     * Preset all the information about the maze like
     * @return
     */
    public byte[] toByteArray(){
        int nRows = this.rows;
        int nColumns = this.columns;

        // Start position
        int startIndexRow = this.getStartPosition().getRowIndex();
        int startIndexColumn = this.getStartPosition().getColumnIndex();
        // End position
        int endIndexRow = this.getGoalPosition().getRowIndex();
        int endIndexCol = this.getGoalPosition().getColumnIndex();

        int size = nRows*nColumns + 12; //  The number of byte that require to represent a maze
        byte[] compressedMaze = new byte[size];

        compressedMaze[0] = (byte)(nRows / 127);
        compressedMaze[1] = (byte)(nRows % 127);
        compressedMaze[2] = (byte)(nColumns / 127);
        compressedMaze[3] = (byte)(nColumns % 127);

        compressedMaze[4] = (byte)(startIndexRow / 127);
        compressedMaze[5] = (byte)(startIndexRow %127);
        compressedMaze[6] = (byte)(startIndexColumn / 127);
        compressedMaze[7] = (byte)(startIndexColumn % 127);

        compressedMaze[8] = (byte)(endIndexRow / 127);
        compressedMaze[9] = (byte)(endIndexRow % 127);
        compressedMaze[10] = (byte)(endIndexCol / 127);
        compressedMaze[11] = (byte)(endIndexCol % 127);

        int index = 12;
        for(int row = 0; row < nRows; row++)
        {
            for(int col = 0; col < nColumns; col++)
            {
                compressedMaze[index] = (byte)this.mazeArray[row][col];
                index++;
            }
        }
        return compressedMaze;
    }

    public void setStart(Position start) {
        this.StartPosition = start;
    }

    public void setEnd(Position goal) {
        this.GoalPosition = goal;
    }

    /**
     *
     * @return maze index [row][col]
     */
    public int[][] getMazeArray() {return this.mazeArray;}

    /**
     *
     * @return row index
     */
    public int getRows() { return this.rows;}

    /**
     *
     * @return column index
     */
    public int getColumns() {return this.columns;}

    /**
     *
     * @return start position
     */
    public Position getStartPosition() {return this.StartPosition;}

    /**
     *
     * @return goal position
     */
    public Position getGoalPosition() {return this.GoalPosition;}

    /**
     * Insert value to cell
     * @param row
     * @param column
     * @param value
     */
    public void setValueToCell(int row, int column, int value){
        this.mazeArray[row][column] = value;
    }

    /**
     * define start and end position to the maze.
     */
    public void defStartEndPos(){
        int side=(int)Math.floor(Math.random() * 2);
        int randGoalRow=(int)Math.floor(Math.random() * rows);
        int randGoalCol=(int)Math.floor(Math.random() * columns);
        while(!isValidEndPos(randGoalRow,randGoalCol))
        {
            randGoalRow=(int)Math.floor(Math.random() * rows);
            randGoalCol=(int)Math.floor(Math.random() * columns);
        }
        switch (side) {
            case 0 -> { //top & bottom
                this.StartPosition = new Position(0, (int) Math.floor(Math.random() * columns));
                this.GoalPosition = new Position(this.rows - 1, randGoalCol);
            }
            case 1 -> { //left & right
                this.StartPosition = new Position((int) Math.floor(Math.random() * rows), 0);
                this.GoalPosition = new Position(randGoalRow, this.columns - 1);
            }
        }
        // To ensure that the start and goal position is not a wall.
        this.mazeArray[this.StartPosition.getRowIndex()][this.StartPosition.getColumnIndex()] = 0;
        this.mazeArray[this.GoalPosition.getRowIndex()][this.GoalPosition.getColumnIndex()] = 0;
    }

    /**
     * Ensure that the position is not in a wall line
     * @param row
     * @param col
     * @return boolean
     */
    public boolean isValidEndPos(int row, int col){
        return row % 2 != 1 && col % 2 != 1;
    }

    /**
     * Ensure that is valid to move to this position (not wall and inside the frame.)
     * @param row
     * @param col
     * @return boolean
     */
    public boolean isValidMove(int row, int col)
    {
        if (row < 0 || col < 0 || row >= this.rows || col >= this.columns){return false;}
        return this.mazeArray[row][col] != 1;
    }

    /**
     * Print the maze
     */
    public void print(){
        System.out.println("{");
        for(int row = 0; row < this.rows; row++) {
            System.out.print("\t{");
            for (int col = 0; col < this.columns; col++) {
                if (this.StartPosition.getRowIndex() == row && this.StartPosition.getColumnIndex() == col) {
                    System.out.print("S");
                } else if (this.GoalPosition.getRowIndex() == row && this.GoalPosition.getColumnIndex() == col) {
                    System.out.print("E");
                } else
                    System.out.print(this.mazeArray[row][col]);
                if (col < this.columns - 1) {
                    System.out.print(",");
                }
            }
            if (row < this.rows - 1) {
                System.out.println("},");
            }
            else
                System.out.println("}");
        }
        System.out.println("};");
    }
}