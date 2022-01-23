package thinkingJava.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 15151 on 2019/2/19.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
    int id();

    /**
     * 只有这样才嫩他在只使用id ，因为给了默认值
     * @return
     */
    String description() default "no description";
}
