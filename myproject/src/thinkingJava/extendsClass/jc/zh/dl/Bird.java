package thinkingJava.extendsClass.jc.zh.dl;

/**
 * 采用继承的方式来复用
 */
public class Bird extends Animal{
    public void fly(){
        //鸟类独有的方法
        System.out.println("-----bird can fly-----");
    }

    public static void main(String[] args) {
        Bird bird = new Bird();
        bird.sing();
        bird.fly();
    }
}
