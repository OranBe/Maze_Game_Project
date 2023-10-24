package algorithms.maze3D;

public class Position3D {
    private final int depthIndex;
    private final int rowIndex;
    private final int columnIndex;

    /**
     * Constructor
     * @param depthIndex
     * @param rowIndex
     * @param columnIndex
     */
    public Position3D(int depthIndex, int rowIndex, int columnIndex) {
        this.depthIndex = depthIndex;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    /**
     *
     * @return depth index
     */
    public int getDepthIndex() {
        return depthIndex;
    }

    /**
     *
     * @return row index
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     *
     * @return column index
     */
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     *
     * @return string
     */
    @Override
    public String toString() {return "{" + depthIndex + "," + rowIndex + "," + columnIndex + "}";
    }
}
