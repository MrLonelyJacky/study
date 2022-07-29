package java8.chapter7;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author segi
 * @date 2022/1/5
 * @description
 */
public class Letter {
    public static String addHeader(String text){
        return "header "+text;
    }
    public static String addFooter(String text){
        return "footer "+text;
    }
    public static String checkSpelling(String text){
        return text.replaceAll("lameda","lambda");
    }

    public static void main(String[] args) {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> allFunction = addHeader.andThen(Letter::addFooter).andThen(Letter::checkSpelling);
        String lameda_is_very_good = allFunction.apply("lameda is very good");
        System.out.println(lameda_is_very_good);
    }


}
