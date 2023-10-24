package algorithms.mazeGenerators;

public interface IMazeGenerator {
    /**
     * Generate a maze according to needs
     * @param rows
     * @param columns
     * @return Maze
     */
    Maze generate(int rows, int columns);

    /**
     * Calculate the time it takes to generate a maze in milliseconds
     * @param rows
     * @param columns
     * @return long (MeasureAlgorithmTime)
     */
    long measureAlgorithmTimeMillis(int rows, int columns);
}
