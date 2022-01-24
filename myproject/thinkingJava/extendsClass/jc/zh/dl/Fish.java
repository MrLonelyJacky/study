package thinkingJava.extendsClass.jc.zh.dl;

/**
 * Created by 15151 on 2019/2/23.
 */
public class Fish {
    private Animal animal;

    public Fish() {
        this.animal = new Animal();
    }

    public void swim(){
        System.out.println("---fish can swim---");
    }

    public void fishBreath(){
        System.out.println("fish use sai");
        animal.breath();
    }

    public static void main(String[] args) {
        Fish smallFish = new Fish();
        smallFish.fishBreath();
        smallFish.swim();
    }
}
