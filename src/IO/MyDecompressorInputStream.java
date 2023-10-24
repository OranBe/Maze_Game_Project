package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyDecompressorInputStream extends InputStream {

    private InputStream in;

    /**
     * Constructor
     * @param in input stream
     */
    public MyDecompressorInputStream(InputStream in) {
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
        if (b==null){return 1;}
        byte[] copy = new byte[b.length];
        this.in.read(copy);

        System.arraycopy(copy, 0, b, 0, 12);
        int rows = b[0]*127+b[1];
        int cols = b[2]*127+b[3];
        int size = ((rows*cols)/8 + 1);
        ArrayList<int[]> cells = new ArrayList<>();

        for (int i = 12; i < size + 12 ; i++) {
            byte curByte = copy[i];
            int[] bits = getBits(curByte);
            cells.add(bits);
        }
        int index=12;
        for (int[] cell : cells) {
            int j = 0;
            while (j < cell.length && index < b.length) {
                b[index] = (byte) cell[j];
                index++;
                j++;
            }
        }
        return 0;
    }

    public int[] getBits(byte curByte) {
        int[] bitsList = new int[8];
        int x = curByte;
        if(x<0){x+=256;}

        int index=7;
        while (x > 0)
        {
            int tmp = x%2;
            x /= 2;
            bitsList[index] = tmp;
            index--;
        }
        return bitsList;
    }
}
