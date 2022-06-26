package spring.chapter7.introduce;

import java.lang.annotation.*;


/**
 * a marked annotation
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoConfig {

    String version();
}
