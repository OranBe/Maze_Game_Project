package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server implements IServerStrategy {

    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool; // Thread pool


    /**
     * Constructor
     * @param port Port from server
     * @param listeningIntervalMS Time between listening in millisecond
     * @param strategy Which strategy work with
     */
    public Server(int port, int listeningIntervalMS, IServerStrategy strategy){
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.stop = false;
        Configurations.getInstance();
        this.threadPool = Executors.newFixedThreadPool(Configurations.numberOfThreads());
    }

    public void start(){
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                serverSocket.setSoTimeout(listeningIntervalMS);
                while (!stop) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        threadPool.execute(() -> {
                            handleClient(clientSocket);
                        });

                    } catch (SocketTimeoutException e){}
                }
            }
            catch (IOException e) {
            }
            finally {
                threadPool.shutdown();
            }
        }).start();
    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e){ }
    }


    public void stop(){
        stop = true;
    }


    /**
     *
     * @param inputStream inputStream
     * @param outputStream OutputStream
     * @throws IOException IO exception
     */
    @Override
    public void ServerStrategy(InputStream inputStream, OutputStream outputStream) throws IOException { }
}
