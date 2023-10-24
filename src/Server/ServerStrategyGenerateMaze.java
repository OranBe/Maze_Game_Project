package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    public ServerStrategyGenerateMaze() {
    }

    /**
     *
     * @param inputStream inputStream
     * @param outputStream OutputStream
     * @throws IOException IO exception
     */
    @Override
    public void ServerStrategy(InputStream inputStream, OutputStream outputStream) throws IOException {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MyCompressorOutputStream myCompressorOutputStream = new MyCompressorOutputStream(out);

            int[] mazeArray = (int[])fromClient.readObject();
            AMazeGenerator algorithmGenerator = Configurations.getGeneratingAlgorithm(); // From the config file
            Maze maze = algorithmGenerator.generate(mazeArray[0], mazeArray[1]); // row, col

            byte[] byteMaze = maze.toByteArray();
            myCompressorOutputStream.write(byteMaze);
            toClient.writeObject(out.toByteArray());
            toClient.flush();
            toClient.close(); // Output
            fromClient.close(); // Input
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
