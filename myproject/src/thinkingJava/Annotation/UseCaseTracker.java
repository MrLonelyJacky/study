package thinkingJava.Annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 15151 on 2019/2/19.
 */
public class UseCaseTracker {
    public static void trackUseCase(List<Integer> useCases, Class<?> c1) {
        Method[] methods = c1.getDeclaredMethods();

    }

    public static void track(List<Integer> useCases, Class<?> cl) {
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            UseCase annotation = method.getAnnotation(UseCase.class);
            if (annotation != null) {
                System.out.println("found useCase id = " + annotation.id() + "desciption = " + annotation.description());

                //useCases.remove(new Integer(annotation.id()));
            }
        }
        for (Integer usecase : useCases) {
            System.out.println(usecase);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        track(list, Passwordutils.class);
        List<?> list1 = new ArrayList<>();
        // list1.add(new ListMaker<thinkingJava.String>());
        list1.add(null);
    }
}
