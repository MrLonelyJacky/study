package effectiveJava.chapter9;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Administrator on 2020/3/11.
 * use interface quote object better than class
 * the program will be more flexible
 * but sometimes interface is not suitable
 * 1.value class like string
 * 2.class-based framework
 * 3.method the interface not have
 */
public class InterfaceQuoteBetter {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set = new LinkedHashSet<>();
    }
}
