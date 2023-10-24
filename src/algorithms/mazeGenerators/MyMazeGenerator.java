package algorithms.mazeGenerators;

import java.io.Serializable;

public class MyMazeGenerator extends AMazeGenerator implements Serializable {

    /**
     * Generate a binary tree maze according to needs
     * According to instructor define minimize values 2
     * @param rows
     * @param columns
     * @return Maze
     */
    @Override
    public Maze generate(int rows, int columns) {
        if (rows < 2 ) rows = 2;
        if (columns < 2 ) columns = 2;
        Maze myMaze = new Maze(rows, columns);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                myMaze.setValueToCell(row,col,1);
            }
        }
        for (int row = 0; row < rows; row+=2) {
            for (int col = 0; col < columns; col+=2) {
                myMaze.setValueToCell(row,col,0);
                if (row-1 > 0 && col-1 > 0){ // not in frame, can go top or left
                    int connRand = (int) Math.round( Math.random() )  ;
                    if (connRand==0){
                        myMaze.setValueToCell(row-1,col,0);
                    }
                    else {
                        myMaze.setValueToCell(row,col-1,0);
                    }
                }
                else if (row-1 > 0) {  // can move only top
                    myMaze.setValueToCell(row-1,col,0);
                }
                else if (col-1 > 0) { // can move only left
                    myMaze.setValueToCell(row,col-1,0);
                }
            }
        }
//        myMaze.setValueToCell(0,0,1);
//        myMaze.setValueToCell(0,1,1);
//        myMaze.setValueToCell(0,2,0);
//        myMaze.setValueToCell(0,3,1);
//        myMaze.setValueToCell(1,0,0);
//        myMaze.setValueToCell(1,1,1);
//        myMaze.setValueToCell(1,2,0);
//        myMaze.setValueToCell(1,3,1);
//        myMaze.setValueToCell(2,0,0);
//        myMaze.setValueToCell(2,1,1);
//        myMaze.setValueToCell(2,2,0);
//        myMaze.setValueToCell(2,3,1);
//        myMaze.setValueToCell(3,0,0);
//        myMaze.setValueToCell(3,1,1);
//        myMaze.setValueToCell(3,2,0);
//        myMaze.setValueToCell(3,3,1);

//        myMaze.setValueToCell(0,0,0);
//        myMaze.setValueToCell(0,1,1);
//        myMaze.setValueToCell(1,0,0);
//        myMaze.setValueToCell(1,1,1);

        myMaze.defStartEndPos();
        return myMaze;
    }
}