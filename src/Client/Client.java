package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements IClientStrategy{

    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy clientStrategy;

    /**
     * Constructor
     * @param serverIP Ip from server
     * @param serverPort Port from server
     * @param strategy Which strategy work with
     */
    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.clientStrategy = strategy;
    }


    /**
     *
     * @param inFromServer inputStream
     * @param outToServer OutputStream
     * @throws IOException IO exception
     */
    @Override
    public void clientStrategy(InputStream inFromServer, OutputStream outToServer) throws IOException {
    }


    public void communicateWithServer(){
        try(Socket serverSocket = new Socket(serverIP, serverPort)){
            System.out.println("connected to server - IP = " + serverIP + ", Port = " + serverPort);
            clientStrategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
