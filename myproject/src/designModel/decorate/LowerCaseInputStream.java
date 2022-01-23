package designModel.decorate;

import java.io.*;
import java.util.HashMap;

public class LowerCaseInputStream extends FilterInputStream {
    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    protected LowerCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int read = super.read();
        return (read==-1?read:Character.toLowerCase(read));
    }

    public int read(byte[]b,int offset,int len) throws IOException {
        int read = super.read(b,offset,len);
        for (int i=offset;i<offset+read;i++){
            b[i] = (byte) Character.toLowerCase(b[i]);
        }
        return read;
    }

    public static void main(String[] args) throws IOException {

        int c;
        InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("test.txt")));
        while ((c=in.read())>=0){
            System.out.println((char) c);
        }
        in.close();
    }
}
