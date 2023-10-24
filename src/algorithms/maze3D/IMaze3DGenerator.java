package algorithms.maze3D;

/**
 * In the end of page 14, write Interface with the name IMazeGenerator3D
 * but in page 18 the picture have Interfaced with the name IMaze3DGenerator
 * we assume that page 18 is the current name of this interface.
 */
public interface IMaze3DGenerator {
    /**
     * Generate a maze according to needs
     * @param depth
     * @param row
     * @param column
     * @return Maze3D
     */
    Maze3D generate(int depth, int row, int column);

    /**
     * Calculate the time it takes to generate a maze in milliseconds
     * @param depth
     * @param row
     * @param column
     * @return long
     */
    long measureAlgorithmTimeMillis(int depth, int row, int column);

}
