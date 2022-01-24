package refactoring.chapter4;

import effectiveJava.chapter6.Test;
import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTester extends TestCase {

    private FileReader _input;

    public FileReaderTester(String name) {
        super(name);
    }

    @Override
    protected void setUp() {
        try {
            _input = new FileReader("data.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("unable to open file");
        }
    }

    @Override
    protected void tearDown() {
        try {
            _input.close();
        }catch (IOException e){
            throw new RuntimeException("error on closing test file");
        }
    }

    @Test
    public void testRead() throws IOException {
        char ch = '&';
        for (int i = 0; i < 7; i++) {
            ch = (char) _input.read();
        }
        System.out.println(ch);
        //assert ('2' == ch);
        assertEquals('d', ch);
    }

    @Test
    public void testReadAtEnd() throws IOException {
        char ch = '&';
        for (int i = 0; i < 7; i++) {
            ch = (char) _input.read();
        }
        assertEquals(-1, _input.read());
    }

    /*public static Test suite() {
        TestSuite testSuite = new TestSuite();
        testSuite.addTest(new FileReaderTester("testRead"));
        testSuite.addTest(new FileReaderTester("testReadAtEnd"));
        return testSuite;
    }*/

    /**
     * 有时候需要根据边界进行测试，对于read而言边界应该是第一个字符，最后一个字符和
     * 倒数第二的字符
     */
    @Test
    public void testReadBoundaries() throws IOException {
        assertEquals("read first char", 'B', _input.read());
        char ch;
        for (int i = 0; i < 5; i++) {
            ch = (char) _input.read();
        }
        assertEquals("read last char", 'd', _input.read());
        assertEquals("read the end",-1, _input.read());
    }


}
