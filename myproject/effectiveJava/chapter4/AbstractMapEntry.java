package effectiveJava.chapter4;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 15151 on 2020/1/7.
 * skeletal implementation class
 * 接口的好处：1、使得容易更新实现新接口 java单继承多实现
 *
 * 2、接口是定义混型的理想选择 混型指的是某种基本类型下实现了的新类型如comparable 和 cloneable
 * 3、接口可以定义非层次结构的类型框架
 * 4、通过包装类的例子可以看出 接口使得安全的拓展增强类功能成为可能
 */
public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {
    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Map.Entry)) {
            return false;
        }
        Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
        return Objects.equals(e.getKey(), getKey()) && Objects.equals(e.getValue(), getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
    }


}
