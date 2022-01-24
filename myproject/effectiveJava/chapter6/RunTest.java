package effectiveJava.chapter6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by 15151 on 2020/2/9.
 * 测试Sample类运行结果
 */
public class RunTest {
    public static void main(String[] args) throws ClassNotFoundException {
        int test = 0;
        int passed = 0;
        Class<?> testClass = Sample.class;
        for (Method m : testClass.getDeclaredMethods()) {
            //System.out.println(m.getName());
            if (m.isAnnotationPresent(Test.class)) {
                test++;
                try {
                    m.invoke(null);
                    passed++;
                    //System.out.println(m.getName() + ":passed");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.printf("Passed：%d,Failed：%d%n", passed, test - passed);
        int test2 = 0;
        int passed2 = 0;
        Class<?> testClass2 = Sample2.class;
        for (Method m : testClass2.getDeclaredMethods()) {
            //System.out.println(m.getName());
            if (m.isAnnotationPresent(ExceptionTest.class)) {
                test2++;
                try {
                    m.invoke(null);
                    System.out.println("test fail no exception");
                } catch (Exception e) {
                    Throwable cause = e.getCause();
                    Class<? extends Throwable> value = m.getAnnotation(ExceptionTest.class).value();
                    if (value.isInstance(cause)) {
                        passed2++;
                    }else {
                        System.out.println("test failed not is exception："+m.getName());
                    }
                }
            }
        }
        System.out.printf("Passed：%d,Failed：%d%n", passed2, test2 - passed2);
    }
}
