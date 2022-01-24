package thinkingJava.genericParadigm.buchang;

/**
 * Created by 15151 on 2019/4/30.
 */
public class InstantiateGenericType {
    public static void main(String[] args) {
        ClassAsFactory<Employee> classAsFactory = new ClassAsFactory<>(Employee.class);
        System.out.println("succeedd");
        //必定报错 因为Integer没有构造方法
        ClassAsFactory<Integer> integerClassAsFactory = new ClassAsFactory<>(Integer.class);
    }
}
