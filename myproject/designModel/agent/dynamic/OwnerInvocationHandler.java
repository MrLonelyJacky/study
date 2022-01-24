package designModel.agent.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理实现逻辑的调用类，处理器类，他控制被代理对象，别人不能修改某些信息，只能获取
 * 比如你不能修改自己的喜好度，因为喜好度是由别人给你的评价
 */
public class OwnerInvocationHandler implements InvocationHandler {

    private PersonBean personBean;

    public OwnerInvocationHandler(PersonBean personBean) {
        this.personBean = personBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
        try {
            if (method.getName().startsWith("get")) {
                return method.invoke(personBean, args);
            } else if (method.getName().equals("setHotOrNotRating")) {
                throw new IllegalAccessException("不能访问该方法修改别人对你的喜好度！");
            } else if (method.getName().startsWith("set")) {
                return method.invoke(personBean, args);
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PersonBean getOwnerProxy(PersonBean personBean) {
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(), personBean.getClass().getInterfaces()
                , new OwnerInvocationHandler(personBean));
    }

    public static void main(String[] args) {
        PersonBean personBean = new PersonBeanImpl();
        personBean.setName("aaa");
        PersonBean owner = getOwnerProxy(personBean);
        owner.setHotOrNotRating(1);
    }
}
