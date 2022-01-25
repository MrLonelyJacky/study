package java8.chapter7;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author segi
 * @date 2022/1/4
 * @description
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader bufferedReader) throws IOException;
}
