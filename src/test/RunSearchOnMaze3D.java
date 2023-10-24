package test;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.search.*;
import java.util.ArrayList;

public class RunSearchOnMaze3D {
    public static void main(String[] args) {
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze3D = mg.generate(10, 10,10);
//        maze3D.print();

        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze3D);
        long startMeasureAlgorithmTime = System.currentTimeMillis();
        solveProblem(searchableMaze, new BreadthFirstSearch());
        long endMeasureAlgorithmTime = System.currentTimeMillis();
        System.out.println(String.format("BFS time(sec): %s", Math.pow (10, -5)*1.67*60*(endMeasureAlgorithmTime-startMeasureAlgorithmTime)));


        startMeasureAlgorithmTime = System.currentTimeMillis();
        solveProblem(searchableMaze, new DepthFirstSearch());
        endMeasureAlgorithmTime = System.currentTimeMillis();
        System.out.println(String.format("DFS time(sec): %s", Math.pow (10, -5)*1.67*60*(endMeasureAlgorithmTime-startMeasureAlgorithmTime)));

        startMeasureAlgorithmTime = System.currentTimeMillis();
        solveProblem(searchableMaze, new BestFirstSearch());
        endMeasureAlgorithmTime = System.currentTimeMillis();
        System.out.println(String.format("BEST time(sec): %s", Math.pow (10, -5)*1.67*60*(endMeasureAlgorithmTime-startMeasureAlgorithmTime)));
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        SearchableMaze3D Sm= (SearchableMaze3D)domain;///////
//        Sm.printSolution( solutionPath);////////
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
    }
}
