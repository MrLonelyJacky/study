package thinkingJava.RTTI.PetDemo;

import java.util.List;

/**
 * Created by 15151 on 2019/4/22.
 */
public class Pets {
    public static final PetCreater creater = new LiteralPetCreater();

    public static Pet randomPet() {
        return creater.randomPet();
    }

    public static Pet[] createArray(int size) {
        return creater.createArray(size);
    }

    public static List<Pet> list(int size){
        return creater.petList(size);
    }
}
