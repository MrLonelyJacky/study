package thinkingJava.extendsClass.safe;

/**
 * 从这里看出私有的作用了，虽然子类可以拥有public方法，但是无法拥有private属性
 * 所以父类的数据只有父类才能修改，所以数据安全
 */
public class Son extends Father {
   /* private int i;

    public void setI(int i) {
        i = -1;
    }

    public int getI() {
        return i;
    }*/

    public static void main(String[] args) {
        Son son = new Son();
        son.setI(1);
        System.out.println(son.getI());
    }
}
