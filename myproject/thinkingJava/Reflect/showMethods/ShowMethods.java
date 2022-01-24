package thinkingJava.Reflect.showMethods;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Created by 15151 on 2019/4/24.
 */
public class ShowMethods {
    private static String usage =
            "usage" +
                    "showMethods qualified.class.name\n" +
                    "To show all methods in class or:\n" +
                    "showMethods qualified.class.name word\n" +
                    "To thinkingJava.search for methods involving 'word'";
    private static Pattern pattern = Pattern.compile("\\w+\\.");

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println(usage);
            System.exit(0);
        }
        int lines = 0;
        try {
            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor<?>[] constructors = c.getConstructors();
            if (args.length == 1) {
                for (Method method : methods) {
                    System.out.println(pattern.matcher(method.toString()).replaceAll(""));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
