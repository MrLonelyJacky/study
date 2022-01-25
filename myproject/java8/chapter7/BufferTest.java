package java8.chapter7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.IntPredicate;

/**
 * @author segi
 * @date 2022/1/4
 * @description
 */
public class BufferTest {
    public static void main(String[] args) throws IOException {
        processFile((BufferedReader br)-> br.readLine()+br.readLine());
        IntPredicate evenNumbers = i->i%2==0;
        Function<Integer,Integer> addOne = x->x+1;
        Function<Integer,Integer> doubleMultipy = x->x*2;
        Function<Integer, Integer> andThen = addOne.andThen(doubleMultipy);
        andThen.apply(1);
    }


    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("a.txt"))){
            return processor.process(bufferedReader);
        }
    }
}
