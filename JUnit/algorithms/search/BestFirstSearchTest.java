package algorithms.search;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    private BestFirstSearch bfs = new BestFirstSearch();
    private Maze mazeTest;
    private SearchableMaze searchableMaze;
    private MyMazeGenerator mg = new MyMazeGenerator();
    private SearchableMaze nullSearchableMaze;

    // To avoid duplication create a constructor

    /**
     * constructor
     */
    public void createMazeTest(){
        mazeTest = mg.generate(100,100);
        searchableMaze = new SearchableMaze(mazeTest);
    }

    /**
     * Get name
     * @throws Exception
     */
    @Test
    void getName() throws Exception{
        assertEquals(bfs.getName(), "BestFirstSearch");
    }

    /**
     * check null solution
     * @throws Exception
     */
    @Test
    void checkNull() throws Exception{
        assertNull(bfs.solve(null));
    }

    /**
     * Test sizes
     * @throws Exception
     */
    @Test
    public void testSizes() throws Exception{
        createMazeTest();
        Assertions.assertEquals(100, searchableMaze.getMaze().getRows());
        Assertions.assertEquals(100, searchableMaze.getMaze().getColumns());
    }

    /**
     * Test empty Maze solution
     * @throws Exception
     */
    @Test
    public void emptyMazeSolution() throws Exception{
        EmptyMazeGenerator emptyMaze = new EmptyMazeGenerator();
        Maze EM = emptyMaze.generate(100,100);
        searchableMaze = new SearchableMaze(EM);
        Solution solveEmptyMaze = bfs.solve(searchableMaze);
        ArrayList<AState> solution = solveEmptyMaze.getSolutionPath();
        Assertions.assertEquals(EM.getGoalPosition().toString(),solution.get(solution.size()-1).toString());
        Assertions.assertEquals(EM.getStartPosition().toString(),solution.get(0).toString());
    }

    /**
     * Test simple Maze solution
     * @throws Exception
     */
    @Test
    public void simpleMazeSolution() throws Exception{
        SimpleMazeGenerator simpleMaze = new SimpleMazeGenerator();
        Maze SM = simpleMaze.generate(100,100);
        searchableMaze = new SearchableMaze(SM);
        Solution solveEmptyMaze = bfs.solve(searchableMaze);
        ArrayList<AState> solution = solveEmptyMaze.getSolutionPath();
        Assertions.assertEquals(SM.getGoalPosition().toString(),solution.get(solution.size()-1).toString());
        Assertions.assertEquals(SM.getStartPosition().toString(),solution.get(0).toString());
    }

    /**
     * Test my Maze solution
     * @throws Exception
     */
    @Test
    public void myMazeSolution() throws Exception{
        createMazeTest();
        searchableMaze = new SearchableMaze(mazeTest);
        Solution solveEmptyMaze = bfs.solve(searchableMaze);
        ArrayList<AState> solution = solveEmptyMaze.getSolutionPath();
        Assertions.assertEquals(mazeTest.getGoalPosition().toString(),solution.get(solution.size()-1).toString());
        Assertions.assertEquals(mazeTest.getStartPosition().toString(),solution.get(0).toString());
    }

    /**
     * Solve maze 2X2
     * @throws Exception
     */
    @Test
    void solveMaze2X2() throws Exception{
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(2, 2);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        ISearchingAlgorithm solver = new BestFirstSearch();
        Solution solution = solver.solve(searchableMaze);

        int nodes = solver.getNumberOfNodesEvaluated();
        ArrayList<AState> solutionPath = solution.getSolutionPath();

        assertEquals(searchableMaze.getGoalState(), solutionPath.get(solutionPath.size()-1));
        assertTrue(nodes>0);
    }

    /**
     * Solve maze 1000X2
     * @throws Exception
     */
    @Test
    void solveMaze1000X2() throws Exception{
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 2);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        ISearchingAlgorithm solver = new BestFirstSearch();
        Solution solution = solver.solve(searchableMaze);

        int nodes = solver.getNumberOfNodesEvaluated();
        ArrayList<AState> solutionPath = solution.getSolutionPath();

        assertEquals(searchableMaze.getGoalState(), solutionPath.get(solutionPath.size()-1));
        assertTrue(nodes>0);
    }

    /**
     * Solve maze 2X1000
     * @throws Exception
     */
    @Test
    void solveMaze2X1000() throws Exception{
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(2, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        ISearchingAlgorithm solver = new BestFirstSearch();
        Solution solution = solver.solve(searchableMaze);

        int nodes = solver.getNumberOfNodesEvaluated();
        ArrayList<AState> solutionPath = solution.getSolutionPath();

        assertEquals(searchableMaze.getGoalState(), solutionPath.get(solutionPath.size()-1));
        assertTrue(nodes>0);
    }

    /**
     * Solve maze 1000X1000
     * @throws Exception
     */
    @Test
    void solveMaze1000X1000() throws Exception{
        long startMeasureAlgorithmTime = System.currentTimeMillis();
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        ISearchingAlgorithm solver = new BestFirstSearch();
        Solution solution = solver.solve(searchableMaze);

        int nodes = solver.getNumberOfNodesEvaluated();
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        long endMeasureAlgorithmTime = System.currentTimeMillis();
        System.out.println(String.format("BEST time(sec): %s", Math.pow (10, -5)*1.67*60*(endMeasureAlgorithmTime-startMeasureAlgorithmTime)));
        assertEquals(searchableMaze.getGoalState(), solutionPath.get(solutionPath.size()-1));
        assertTrue(nodes>0);
    }


}