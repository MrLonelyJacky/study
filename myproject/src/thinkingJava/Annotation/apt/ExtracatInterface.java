package thinkingJava.Annotation.apt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 15151 on 2019/5/10.
 */
@Target({ElementType.TYPE})

@Retention(RetentionPolicy.SOURCE)
public @interface ExtracatInterface {
    String value();
}
