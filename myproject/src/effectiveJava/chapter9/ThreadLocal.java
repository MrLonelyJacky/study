package effectiveJava.chapter9;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/3/8.
 * before java version 1.2  developers think about designing a mechanism to make
 * every thread has it is own local variables. someone puts forward a design scheme  like this
 * */
public class ThreadLocal {
    public ThreadLocal(){

    }

    private static final Map<String,Object> map = new HashMap<>();

    /**
     * it will cause some todo like two clients use the same string will
     * have the same thread because they share with the map
     * @param key
     * @param value
     */
    public static void set(String key,Object value){
        map.put(key,value);
        //pseduo code....
    }
    public static Object get(String key){
        //pseduo code...
        return map.get(key);
    }
    /**
     * the solution is to define a unique key like ThreadLocalChange
     */
}
