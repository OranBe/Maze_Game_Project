package algorithms.maze3D;

public class Maze3D {
    private int [][][] maze3DArray;
    private  int rows;
    private  int columns;
    private  int depths;
    private Position3D StartPosition;
    private Position3D GoalPosition;

    /**
     * Constructor
     * @param depths
     * @param rows
     * @param columns
     */
    public Maze3D(int depths, int rows, int columns) {
        if (depths > 2 || rows > 2 || columns > 2) {
                this.depths = depths;
                this.rows = rows;
                this.columns = columns;
                this.maze3DArray = new int[depths][rows][columns];
        }
    }

    // Get-Set
    /**
     *
     * @return maze index [depth][row][col]
     */
    public int[][][] getMap() {return maze3DArray;}

    /**
     *
     * @return row index
     */
    public int getRows() {return rows;}

    /**
     *
     * @return column index
     */
    public int getColumns() {return columns;}

    /**
     *
     * @return depth index
     */
    public int getDepths() {return depths;}

    /**
     *
     * @return start position
     */
    public Position3D getStartPosition() {return StartPosition;}

    /**
     *
     * @return goal position
     */
    public Position3D getGoalPosition() {return GoalPosition;}

    /**
     * Insert value to cell
     * @param depth
     * @param row
     * @param column
     * @param value
     */
    public void setValueToCell(int depth, int row, int column, int value){this.maze3DArray[depth][row][column] = value;}

    /**
     * define start and end position to the maze.
     */
    public void defStartEndPos(){
        int randGoalDepth=(int)Math.floor(Math.random() * depths);
        int randGoalRow=(int)Math.floor(Math.random() * rows);
        int randGoalCol=(int)Math.floor(Math.random() * columns);
        while(!isValidEndPos(randGoalDepth, randGoalRow,randGoalCol))
        {
            randGoalDepth=(int)Math.floor(Math.random() * depths);
            randGoalRow=(int)Math.floor(Math.random() * rows);
            randGoalCol=(int)Math.floor(Math.random() * columns);
        }

        this.StartPosition = new Position3D(0,0,0);
        this.GoalPosition = new Position3D(this.depths - 1, randGoalRow,randGoalCol);
        // To ensure that the start and goal position is not a wall.
        this.maze3DArray[this.StartPosition.getDepthIndex()][this.StartPosition.getRowIndex()][this.StartPosition.getColumnIndex()] = 0;
        this.maze3DArray[this.GoalPosition.getDepthIndex()][this.GoalPosition.getRowIndex()][this.GoalPosition.getColumnIndex()] = 0;
    }

    /**
     * Ensure that the position is not in a wall line
     * @param depth
     * @param row
     * @param colum
     * @return boolean
     */
    public boolean isValidEndPos(int depth, int row, int colum){
        return depth % 2 == 1 && row % 2 == 1 && colum % 2 == 1;
    }

    /**
     * Ensure that is valid to move to this position (not wall and inside the frame.)
     * @param depth
     * @param row
     * @param colum
     * @return boolean
     */
    public boolean isValidMove(int depth, int row, int colum)
    {
        if (depth < 0 || row < 0 || colum < 0 || depth >= this.depths || row >= this.rows || colum >= this.columns){return false;}
        return this.maze3DArray[depth][row][colum] != 1;
    }

    /**
     * Print the maze
     */
    public void print(){
        System.out.println("{");
        for(int depth = 0; depth < this.depths; depth++) {
            System.out.println("\t{");
            for(int row = 0; row < this.rows; row++) {
                System.out.print("\t\t{");
                for (int col = 0; col < this.columns; col++) {
                    if (this.StartPosition.getDepthIndex() == depth && this.StartPosition.getRowIndex() == row && this.StartPosition.getColumnIndex() == col) {
                        System.out.print("S");
                    } else if (this.GoalPosition.getDepthIndex() == depth && this.GoalPosition.getRowIndex() == row && this.GoalPosition.getColumnIndex() == col) {
                        System.out.print("E");
                    }
                    else
                        System.out.print(this.maze3DArray[depth][row][col]);
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
            System.out.println("\t}");
        }
        System.out.println("};");
    }
}