package test;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.Position3D;

public class RunMaze3DGenerator {
    public static void main(String[] args) {
        testMazeGenerator(new MyMaze3DGenerator());
    }
    private static void testMazeGenerator(IMaze3DGenerator mazeGenerator) {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze3D generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(500/*depth*/,500/*rows*/,500/*columns*/)));
        // generate another maze
        Maze3D maze = mazeGenerator.generate(5/*depth*/,5/*rows*/,5/*columns*/);
        // prints the maze3D
        maze.print();
        // get the maze entrance
        Position3D startPosition = maze.getStartPosition();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{depth,row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition())); // format "{depth,row,column}"
    }
}
