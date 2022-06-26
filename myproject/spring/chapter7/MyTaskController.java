package spring.chapter7;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * 增强实现类
 */
public class MyTaskController extends DelegatingIntroductionInterceptor implements TaskActiver{
    private ThreadLocal<Boolean> isTime = new ThreadLocal<>();

    public MyTaskController() {
        isTime.set(Boolean.FALSE);
    }

    @Override
    public void startTask() {
        isTime.set(Boolean.TRUE);
    }

    @Override
    public void stopTask() {
        isTime.set(Boolean.FALSE);
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object object =null;
        if (isTime.get()){
            MyTask.getTarget().set(methodInvocation.getMethod().getName());
            MyTask.beginRecord();
            object = super.invoke(methodInvocation);
            MyTask.endRecord();
        }else {
            object = super.invoke(methodInvocation);
        }
        return object;
    }


    public static void main(String[] args) {


    }
}
