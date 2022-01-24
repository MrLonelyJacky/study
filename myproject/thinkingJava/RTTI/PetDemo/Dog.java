package thinkingJava.RTTI.PetDemo;

/**
 * Created by 15151 on 2019/4/21.
 */
public class Dog extends Pet{
    public Dog() {
    }

    public Dog(String name) {
        super(name);
    }

    public static void main(String[] args) {
        Dog dog = new Dog("ee");
        System.out.println(dog.getName());
        //System.out.println(Pet.name);
        System.out.println(new Pet().getName());
    }


}
