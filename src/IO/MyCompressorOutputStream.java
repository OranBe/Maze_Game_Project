package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;

    /**
     * Constructor
     * @param out output stream
     */
    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    /**
     *
     * @param b   the {@code byte}.
     * @throws IOException IO
     */
    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    /**
     *
     * @param b   the data.
     * @throws IOException output
     */
    @Override
    public void write(byte[] b) throws IOException{
        byte[] finalCompressedMaze = Compress(b);
        for (byte value : finalCompressedMaze) {
            out.write(value);
        }
    }

    /**
     *
     * @param mazeData value of bytes to compress
     * @return ArrayList of compress data
     */
    public byte[] Compress(byte[] mazeData) {
        ArrayList<Byte> compressedMazeData = new ArrayList();
        // Copy the properties (1-12)
        for (int i = 0; i < 12; i++) {compressedMazeData.add(mazeData[i]);}
        int index = 12;
        int countBit = 0;
        int sum = 0;

        for (int i = index; i < mazeData.length; i++) {
            byte curCell = mazeData[i]; // Can be 0 or 1
            countBit++;
            if (curCell > 0){
                sum += (int) Math.pow(2,(8 - countBit));
            }
            if(countBit == 8)
            {
                compressedMazeData.add(getDecimalValue(sum).byteValue());
                countBit = 0;
                sum = 0;
            }
        }
        // For the remainder
        if (countBit > 0 && countBit < 8)
            compressedMazeData.add(getDecimalValue(sum).byteValue());
        return convertToBytesArray(compressedMazeData);
    }

    /**
     * For the remainder
     * @param compressedMazeData  value of bytes to compress
     * @return arrayList of bytes
     */
    private byte[] convertToBytesArray(ArrayList<Byte> compressedMazeData) {
        int size = compressedMazeData.size();
        byte[] toReturn = new byte[size];
        int i = 0;
        while (i < size) {
            toReturn[i] = compressedMazeData.get(i);
            i++;
        }
        //System.out.println(size);
        return toReturn;
        }

    /**
     *
     * @param num 2^7 + 2^6 + 2^5 + 2^4 + 2^3 + 2^2 + 2^1 + 2^0 (Possible option to be)
     * @return num
     */
    public Integer getDecimalValue(Integer num)
    {
        return num & 0xff;
    }

}
