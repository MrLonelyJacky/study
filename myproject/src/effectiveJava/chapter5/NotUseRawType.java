package effectiveJava.chapter5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2020/4/15.
 * 不要使用原生态类型
 */
public class NotUseRawType {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        unsafeAdd(strings, 4);
        String s = strings.get(0);
    }

    /**
     * 如果你的参数使用的是原生态类型，这很危险，因为会容易产生类型转换异常
     * 倘若你将参数换成List<Object>编译器就会不让通过 继而达到安全
     * @param strings
     * @param o
     */
    private static void unsafeAdd(List strings, Object o) {
        strings.add(o);
    }
}
