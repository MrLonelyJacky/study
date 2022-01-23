package thinkingJava.RTTI.classForName;

/**
 * Created by 15151 on 2019/4/19.
 */
public class ToyTest {
    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("thinkingJava.RTTI.classForName.FancyToy");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        printInfo(c);
        for (Class face: c.getInterfaces()){
           printInfo(face);
        }
        Class up =c.getSuperclass();
        Object obj = null;
        try {
            obj = up.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        printInfo(obj.getClass());
    }

    static void printInfo(Class c){
        System.out.println(c.getName()+":"+c.isInterface());
        System.out.println("simpleName:"+c.getSimpleName());
        System.out.println("canonical name:"+c.getCanonicalName());
    }
}
