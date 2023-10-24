package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {
    /**
     * Generate a maze according to needs
     * @param depth
     * @param row
     * @param column
     * @return Maze3D
     */
    @Override
    public abstract Maze3D generate(int depth, int row, int column);

    /**
     * Calculate the time it takes to generate a maze in milliseconds
     * @param depth
     * @param row
     * @param column
     * @return long (Algorithm time measure in millisecond)
     */
    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long startMeasureAlgorithmTime = System.currentTimeMillis();
        generate(depth,row,column);
        long endMeasureAlgorithmTime = System.currentTimeMillis();
        return endMeasureAlgorithmTime-startMeasureAlgorithmTime;
    }
}
