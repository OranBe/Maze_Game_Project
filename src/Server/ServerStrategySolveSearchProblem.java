package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    private String tempDirectoryPath;
    private HashMap<String,File> mapFile = new HashMap<>();


    /**
     * constructor
     */
    public ServerStrategySolveSearchProblem() {
        this.tempDirectoryPath = System.getProperty("java.io.tmpdir");

        File[] files = new File(tempDirectoryPath).listFiles();
        // Start find the file name if exists
        String fileName = "Solution--";
        assert files != null;
        for (File f: files){
            if (f.getName().startsWith((fileName))){
                // if the filename exists start find if their any solution for this maze
                String fileSoultion =  f.getName();
                mapFile.put(fileSoultion,f);

            }
        }
    }

    /**
     * @param inputStream  inputStream
     * @param outputStream OutputStream
     * @throws IOException IO exception
     */
    @Override
    public void ServerStrategy(InputStream inputStream, OutputStream outputStream) throws IOException {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);

            Maze maze = (Maze) fromClient.readObject();

            Solution solve = saveOrFindSolution(maze);
            toClient.writeObject(solve);
            toClient.flush();
            toClient.close(); // Output
            fromClient.close(); // Input

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find from temp folder all the solution, if the solution found, return
     * else save new solution for this specific maze.
     * @param maze maze
     * @return solution
     * @throws IOException IO
     */
    private Solution saveOrFindSolution(Maze maze) throws IOException {
        Object isSovled = null;
        String save = maze.hashCode() + ArrayToString(maze);

        if (!mapFile.isEmpty() && mapFile.containsKey("Solution--" + save)){
            try {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(mapFile.get("Solution--" + save)));
                isSovled = input.readObject();
                return (Solution) isSovled;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        ISearchable iSearchable = new SearchableMaze(maze);
        Configurations configurations = Configurations.getInstance();
        String algoName = configurations.solutionAlgorithmName();
        ASearchingAlgorithm SearchAlgo = switch (algoName) {
            case "BreadthFirstSearch" -> new BreadthFirstSearch();
            case "DepthFirstSearch" -> new DepthFirstSearch();
            case "BestFirstSearch" -> new BestFirstSearch();
            default -> null;
        };
        try{
            assert SearchAlgo != null;
            isSovled = SearchAlgo.solve(iSearchable);
                save_solution(maze,(Solution) isSovled);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  (Solution) isSovled;
    }

    /**
     * Make name for file with the properties maze.
     * @param maze maze
     * @return string of name
     */
    private String ArrayToString(Maze maze) {
        StringBuilder fileName = new StringBuilder();
        byte[] bytes = maze.toByteArray();
        int sizeCompress = 14; //13 (index12) Is the representation 0 or 1, 14(index13) for counting the number of organs of the first type
        byte[] compressedArray = new byte[sizeCompress];

        // Copy the properties (1-12)
        System.arraycopy(bytes, 0, compressedArray, 0, 12);

        for (int i = 0; i < 12; i++){
            fileName.append(compressedArray[i]).append("-");
        }
        fileName.append(compressedArray.length).append(".txt");
        return fileName.toString();
    }

    /**
     * Need to save the new solution for this maze.
     * @param maze maze
     * @param solution sol
     */
    public void save_solution(Maze maze , Solution solution) {
        String numberOfMaze = maze.hashCode() + ArrayToString(maze);
//        File nameMazePath = new File(tempDirectoryPath+ "Maze--" + numberOfMaze);
        File solutionMazePath = new File(tempDirectoryPath+ "Solution--" + numberOfMaze);
        try{
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(solutionMazePath));

            objOut.writeObject(solution);
            objOut.flush();

            mapFile.put("Solution--" + numberOfMaze, solutionMazePath);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}