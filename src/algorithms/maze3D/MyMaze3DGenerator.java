package algorithms.maze3D;

public class MyMaze3DGenerator extends AMaze3DGenerator {

    /**
     * Generate a binary tree maze according to needs
     * According to instructor define minimize values 3
     * @param depths
     * @param rows
     * @param columns
     * @return Maze
     */
    @Override
    public Maze3D generate(int depths, int rows, int columns) {
        if (depths < 3 ) depths = 3;
        if (rows < 3 ) rows = 3;
        if (columns < 3 ) columns = 3;
        Maze3D my3DMaze = new Maze3D(depths, rows, columns);
        for (int depth = 0; depth < depths; depth++) {
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    my3DMaze.setValueToCell(depth, row, col, 1);
                }
            }
        }
        for (int depth = 0; depth < depths; depth++) {
            for (int row = 0; row < rows; row += 2) {
                for (int col = 0; col < columns; col += 2) {
                    my3DMaze.setValueToCell(depth, row, col, 0);
                    if (row - 1 > 0 && col - 1 > 0) { // not in frame, can go top or left
                        int connRand = (int) Math.round(Math.random());
                        if (connRand == 0) {
                            my3DMaze.setValueToCell(depth, row - 1, col, 0);
                        } else {
                            my3DMaze.setValueToCell(depth, row, col - 1, 0);
                        }
                    } else if (row - 1 > 0) {  // can move only top
                        my3DMaze.setValueToCell(depth, row - 1, col, 0);
                    } else if (col - 1 > 0) { // can move only left
                        my3DMaze.setValueToCell(depth, row, col - 1, 0);
                    }
                }
            }
        }
        my3DMaze.defStartEndPos();
        return my3DMaze;
    }
}
