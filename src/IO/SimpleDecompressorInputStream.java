package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    public InputStream in;

    /**
     * Constructor
     * @param in input stream
     */
    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    /**
     *
     * @return 0
     * @throws IOException input
     */
    @Override
    public int read() throws IOException {return 0;}


    /**
     *
     * @param b   the buffer into which the data is read.
     * @return 0
     * @throws IOException input
     */
    @Override
    public int read(byte[] b) throws IOException {
        byte[] CompressedArray = new byte[b.length+1];
        this.in.read(CompressedArray);

        int index = 12;
        byte oneOrZero = CompressedArray[12];

        // Copy the properties (1-12)
        System.arraycopy(CompressedArray, 0, b, 0, 12);
//        b[12] = CompressedArray[12];
        int counter = 13;
        for (;counter < CompressedArray.length; counter++){

//        while(CompressedArray[counter] != 0 ){
            if (CompressedArray[counter] != 128) { // the code number 128 represent nothing
                for (int i = 0; i < CompressedArray[counter]; i++){
                    b[index] = oneOrZero;
                    index++;
                }
            }
            if (oneOrZero == 1)
                oneOrZero = 0;
            else
                oneOrZero = 1;
//            counter++;
//            if (counter >= CompressedArray.length){
//                if (CompressedArray[counter] == 1){
//                    b[index] = oneOrZero;
//                }
//                break;
//            }
        }
        return 0;
    }
}

