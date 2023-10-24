package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{

    /**
     * Generate an empty maze according to needs
     * According to instructor define minimize values 2
     * @param rows
     * @param columns
     * @return Maze
     */
    @Override
    public Maze generate(int rows, int columns) {
        if (rows < 2 ) rows = 2;
        if (columns < 2 ) columns = 2;
        Maze emptyMaze = new Maze(rows, columns);
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < columns; col++){
                emptyMaze.setValueToCell(row,col,0);
            }
        }
        emptyMaze.defStartEndPos();
        return emptyMaze;
    }
}
