package effectiveJava.chapter5;

/**
 * Created by 15151 on 2020/1/9.
 * 列表优于数组 数组是协变的 泛型是可变的 非具体化的
 * 因此数组和泛型不能很好的混用  如new List<E> ［］ 、new List<String> ［］和new E ［］ 因为擦除和数组协变的关系
 * 禁止泛型数组有些讨厌 这表明泛型一般不能返回它的元素类型数组 也意味着可变参数方法会有一些警告
 * 由于每当调用可变参数方法时，就会创建一个数组来存放varargs 参数
 */
public class LieBiaoBetter {
    public static void main(String[] args) {
        Object[] objects =new Long[1];
        objects[0] = "i dont fit in";//报错 ArrayStoreException
        //List<Object> list = new ArrayList<Long>(); //编译不通过

    }
}
