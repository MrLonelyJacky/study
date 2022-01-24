package thinkingJava.RTTI.PetDemo;

import java.util.HashMap;

/**
 * Created by 15151 on 2019/4/21.
 */
public class PetCount {
    static class PetCounter extends HashMap<String, Integer> {
        public void count(String type) {
            Integer quantity = get(type);
            if (quantity == null) {
                put(type, 1);
            } else {
                put(type, quantity + 1);
            }
        }

    }

    public static void countPets(PetCreater petCreater) {
        PetCounter counter = new PetCounter();
        for (Pet pet : petCreater.createArray(10)) {
            System.out.println(pet.getClass().getSimpleName());
            if (pet instanceof Pet) {
                counter.count("pet");
            }
            if (pet instanceof Cat) {
                counter.count("cat");
            }
            if (pet instanceof Dog) {
                counter.count("dog");
            }
        }
        System.out.println(counter);
    }

    public static void main(String[] args) {
        countPets(new FornameCreater());
    }
}
