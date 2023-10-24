package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {
    /**
     * Generate a simple maze according to needs
     * According to instructor define minimize values 2
     * @param rows
     * @param columns
     * @return Maze
     */
    @Override
    public Maze generate(int rows, int columns) {
        if (rows < 2 ) rows = 2;
        if (columns < 2 ) columns = 2;
        Maze simpleMaze = new Maze(rows, columns);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col ++) {
                if (row%2==0){
                    simpleMaze.setValueToCell(row, col, 0);
                }
                else {
                    simpleMaze.setValueToCell(row, col, 1);
                }
            }
        }
        for (int row = 1; row < rows; row+=2) {
            int col = (int)Math.floor(Math.random() * columns);
            simpleMaze.setValueToCell(row, col, 0);
        }
        simpleMaze.defStartEndPos();
        return simpleMaze;
    }
}
