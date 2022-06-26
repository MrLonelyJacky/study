package spring.chapter7;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectJTest {

    //切入点表达式
    @Pointcut("execution(*.test(..)")
    public void test(){

    }

    @Before("test()")
    public void beforeTest(){
        System.out.println("before test.......");
    }

}
