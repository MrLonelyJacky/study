package effectiveJava.chapter4;

import java.util.Map;

/**
 * Created by 15151 on 2020/1/7.
 */
public class MyEntry<K,V> extends AbstractMapEntry<K,V> implements Map.Entry<K,V>{

    @Override
    public K getKey() {
        return null;
    }

    @Override
    public V getValue() {
        return null;
    }


}
