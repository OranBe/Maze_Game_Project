package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {
    private final int row;
    private final int column;

    /**
     * Constructor
     * @param row
     * @param column
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     *
     * @return row index
     */
    public int getRowIndex() {
        return row;
    }

    /**
     *
     * @return column index
     */
    public int getColumnIndex() {
        return column;
    }

    /**
     *
     * @return string
     */
    @Override
    public String toString() {
        return "{" + row + "," + column + "}";
    }
}
