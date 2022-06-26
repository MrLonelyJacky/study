package spring.chapter7.introduce;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

public class AutoConfigIntroductionInterceptor implements IntroductionInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        return null;
    }

    @Override
    public boolean implementsInterface(Class<?> intf) {
        return false;
    }
}
