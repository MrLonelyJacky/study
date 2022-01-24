package thinkingJava.extendsClass.jc.zh.dl;

/**
 * 采用组合的方式来服用类
 */
public class Dog {
    private Animal animal;
    Dog(Animal animal){
        this.animal = animal;
    }

    public void wang(){
        System.out.println("---dog can wang wang wang ---");
    }

    public static void main(String[] args) {
        Animal animal = new Animal();
        Dog dog = new Dog(animal);
        dog.animal.breath();
        dog.wang();
    }
}
