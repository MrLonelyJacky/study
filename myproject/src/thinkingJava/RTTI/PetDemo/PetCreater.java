package thinkingJava.RTTI.PetDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by 15151 on 2019/4/21.
 * 随机生成pet类
 */
public abstract class PetCreater {
    abstract List<Class<? extends Pet>> types();

    public Pet randomPet() {
        int n = new Random().nextInt(types().size());
        try {
            return types().get(n).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Pet[] createArray(int size) {
        Pet[] result = new Pet[size];
        for (int i = 0; i < size; i++) {
            result[i] = randomPet();
        }
        return result;
    }

    public List<Pet> petList(int size) {
        List<Pet> pets = new ArrayList<>();
        Collections.addAll(pets, createArray(size));
        return pets;
    }
}
