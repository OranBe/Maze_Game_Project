package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class SimpleCompressorOutputStream extends OutputStream {

    public OutputStream out;
    private int counter;

    /**
     * Constructor
     *
     * @param out output stream
     */
    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
        this.counter = 0;
    }

    /**
     * @param b the {@code byte}.
     * @throws IOException
     */
    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }


    /**
     * @param b the data.
     * @throws IOException output
     */
    @Override
    public void write(byte[] b) throws IOException {
        int sizeCompress = 14; //13 (index12) Is the representation 0 or 1, 14(index13) for counting the number of organs of the first type
        int indexB = 12; // Index 1 to 12 are general properties of maze (nRows, nColumns, startIndexRow, startIndexColumn, endIndexRow, endIndexCol)
        int indexCompress = 13;
        int previousNum = b[12];
        while (indexB < b.length) { // Find the expected size of the compress array
            if (b[indexB] != previousNum) {
                sizeCompress++;
                this.counter = 0;
            } else if (this.counter == 126) {
                sizeCompress += 2;
                this.counter = 0;
            }
            this.counter++;
            previousNum = b[indexB];
            indexB++;
        }

        this.counter = 0;
        byte[] compressedArray = new byte[sizeCompress];

        // Copy the properties (1-12)
        System.arraycopy(b, 0, compressedArray, 0, 12);
        indexB = 12;
        compressedArray[12] = b[12];//12 Is the representation start 0 or 1
        previousNum = b[12];
        while (indexB < b.length) {
            if (b[indexB] == previousNum) {
                if (this.counter == 126) {
                    compressedArray[indexCompress] = (byte) (this.counter);
                    compressedArray[indexCompress + 1] = (byte) (128);
                    indexCompress += 2;
                    this.counter = 0;
                }
                this.counter++;
                indexB++;
            } else {
                compressedArray[indexCompress] = (byte) (this.counter);
                this.counter = 0;
                indexB++;
                indexCompress++;
                this.counter++;
            }
            previousNum = b[indexB - 1];
        }
        compressedArray[indexCompress] = (byte) (this.counter); // last iteration
        this.out.write(compressedArray);
   }
}