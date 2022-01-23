package effectiveJava.chapter6;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 15151 on 2020/2/4.
 * 展示注解优于命名模式 只在抛异常时触发
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionTest {
    Class<? extends Throwable> value();
}
