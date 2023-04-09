package com.jacky.springframework.aop;

import com.jacky.springframework.aop.framework.MyAopProxy;
import com.jacky.springframework.aop.framework.MyJdkDynamicAopProxy;

/**
 * 代理工厂 暂未实现cglib
 */
public class MyProxyFactory {
    private MyAdvisedSupport advisedSupport;

    public MyProxyFactory(MyAdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private MyAopProxy createAopProxy() {
        /*if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }*/

        return new MyJdkDynamicAopProxy(advisedSupport);
    }


}
