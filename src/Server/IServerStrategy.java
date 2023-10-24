package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IServerStrategy {

    /**
     *
     * @param inputStream inputStream
     * @param outputStream OutputStream
     * @throws IOException IO exception
     */
    void ServerStrategy(InputStream inputStream, OutputStream outputStream) throws IOException;
}
