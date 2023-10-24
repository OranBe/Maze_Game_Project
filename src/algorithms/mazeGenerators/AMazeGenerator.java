package algorithms.mazeGenerators;

import java.io.Serializable;

public abstract class AMazeGenerator implements IMazeGenerator , Serializable {
    /**
     * Generate a maze according to needs
     * @param rows
     * @param columns
     * @return Maze
     */
    @Override
    public abstract Maze generate(int rows, int columns);

    /**
     * Calculate the time it takes to generate a maze in milliseconds
     * @param rows
     * @param columns
     * @return long (MeasureAlgorithmTime)
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long startMeasureAlgorithmTime = System.currentTimeMillis();
        generate(rows,columns);
        long endMeasureAlgorithmTime = System.currentTimeMillis();
        return endMeasureAlgorithmTime-startMeasureAlgorithmTime;
    }
}
