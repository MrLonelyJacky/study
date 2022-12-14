package spring.chapter7.advisor;

import org.aopalliance.aop.Advice;
import org.springframework.aop.*;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.stereotype.Component;
import spring.chapter7.introduce.AutoConfig;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: jacky
 * @create: 2022-11-18 11:30
 **/
@Component
public class MyAdvisor implements PointcutAdvisor {

    @Override
    public Pointcut getPointcut() {
        return new Pointcut() {

            @Override
            public ClassFilter getClassFilter() {
                return ClassFilter.TRUE;
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        return method.isAnnotationPresent(AutoConfig.class);
                    }

                    @Override
                    public boolean isRuntime() {
                        return false;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        return false;
                    }
                };
            }
        };
    }

    @Override
    public Advice getAdvice() {
        return new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("hello my girl");
            }
        };
    }

    @Override
    public boolean isPerInstance() {
        return true;
    }
}
