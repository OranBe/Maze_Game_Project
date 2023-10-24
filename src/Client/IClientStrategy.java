package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface  IClientStrategy {

    /**
     *
     * @param inFromServer inputStream
     * @param outToServer OutputStream
     * @throws IOException IO exception
     */
    void clientStrategy(InputStream inFromServer, OutputStream outToServer) throws IOException;
}
