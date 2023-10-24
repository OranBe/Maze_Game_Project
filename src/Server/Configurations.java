package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import java.io.*;
import java.util.Properties;

public class Configurations {

    private static Configurations instance = null;
    static Properties properties = new Properties();
    private static InputStream inputStream;
    OutputStream outputStream;

    /**
     * EmptyMazeGenerator, SimpleMazeGenerator, MyMazeGenerator
     * BreadthFirstSearch, DepthFirstSearch, BestFirstSearch
     * Constructor
     * @throws IOException IO
     */
    private  Configurations() throws IOException {

        outputStream = new FileOutputStream("resources/config.properties");
        properties.setProperty(("threadPoolSize"), ("10"));
        properties.setProperty(("mazeGeneratingAlgorithm"),("MyMazeGenerator"));
        properties.setProperty("mazeSearchingAlgorithm", "BestFirstSearch");
        properties.store(outputStream,"config the resources/config.properties");
    }


    /**
     * Singleton, make new instance
     * @return Configurations instance
     */
    public static Configurations getInstance(){
        if (instance == null)
        {
            try {
                instance = new Configurations();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * From the config file, get number of threads
     * @return number of threads
     */
    public static int numberOfThreads() {
        String numOfThreads = String.valueOf(5);
        try {
            inputStream = new FileInputStream("resources/config.properties");
            properties.load(inputStream);
            numOfThreads = properties.getProperty("threadPoolSize");
        }
        catch (Exception ignored){
        }
        return Integer.parseInt(numOfThreads);
    }

    /**
     * From the config file, get generate algorithm
     * @return Maze generator
     * @throws IOException IO
     */
    public static AMazeGenerator getGeneratingAlgorithm() throws IOException {
        inputStream = new FileInputStream("resources/config.properties");
        properties.load(inputStream);
        String GeneratingAlgorithm = properties.getProperty("mazeGeneratingAlgorithm");

        if (GeneratingAlgorithm.equals("EmptyMazeGenerator"))
            return new EmptyMazeGenerator();
        else if(GeneratingAlgorithm.equals("SimpleMazeGenerator"))
            return new SimpleMazeGenerator();
        else
            return new MyMazeGenerator();
    }

    /**
     * From the config file, get search algorithm
     * @return which maze run
     */
    public String solutionAlgorithmName(){
        return properties.getProperty("mazeSearchingAlgorithm");
    }

    /**
     * From the config file, get generate algorithm
     * @return search algorithm
     * @throws IOException IO
     */
    public static ISearchingAlgorithm getSearchingAlgorithm() throws IOException {
        inputStream = new FileInputStream("resources/config.properties");
        properties.load(inputStream);
        String searchAlgo = properties.getProperty("mazeSearchingAlgorithm");

        if(searchAlgo.equals("BreadthFirstSearch"))
            return new BreadthFirstSearch();
        else if (searchAlgo.equals("DepthFirstSearch"))
            return new DepthFirstSearch();
        else
            return new BestFirstSearch();
    }

}
