package thinkingJava.genericParadigm.mixedTypeProxy;

import thinkingJava.genericParadigm.Mocha;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 15151 on 2019/5/7.
 */
public class MinProxy implements InvocationHandler {
    Map<String,Object> delegatesByMethod;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }

    public static void main(String[] args) {
        Map<String, List<?>> map = new HashMap<>();
        List<Integer> list2 = new ArrayList<>();
        map.put("1", list2);
        List<Object> list = new ArrayList<>();
        list.add(new Mocha());
        list.add("aa");
        List<?> aa = new ArrayList<>();
        //aa.add("aa");
    }
}
