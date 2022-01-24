package effectiveJava.chapter12;

import java.math.BigInteger;

/**
 * Created by 15151 on 2020/3/24.
 * think about using custom serialize form
 * if a class uses default serialize form  and u want in the late version drop
 * the class implement will fail like BigInteger 他继承Number并将序列化默认继承过来
 * 就不能随意丢弃要实现的Number了
 */
public class CustomSerialize {
    public static void main(String[] args) {

    }
}
