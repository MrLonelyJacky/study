package thinkingJava.genericParadigm.typeDeduction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 15151 on 2019/4/28.
 */
public class New {
    public static <K, V> Map<K, V> map() {

        return new HashMap<K, V>();
    }
}
