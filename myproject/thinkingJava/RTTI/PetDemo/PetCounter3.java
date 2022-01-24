package thinkingJava.RTTI.PetDemo;

import java.util.HashMap;

/**
 * Created by 15151 on 2019/4/22.
 */
public class PetCounter3 {
    static class PetCounter extends HashMap<String, Integer> {
        public PetCounter() {
            super();
        }

        /*public void count(Pet pet) {
            for (thinkingJava.Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
                if (pair.getKey().isInstance(pet)) {

                }
            }
        }*/
    }
}
