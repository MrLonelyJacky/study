package thinkingJava.RTTI.PetDemo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by 15151 on 2019/4/22.
 * 字面量的方式创建pet类
 */
public class LiteralPetCreater extends PetCreater {
    public static final List<Class<? extends Pet>> allTypes =
            Collections.unmodifiableList(Arrays.asList(Pet.class, Dog.class, Cat.class,ErHa.class,Miao.class));

    private static final List<Class<? extends Pet>> types = allTypes.subList(allTypes.indexOf(Dog.class),allTypes.size());


    @Override
    List<Class<? extends Pet>> types() {
        return types;
    }

    public static void main(String[] args) {
        System.out.println(types);
    }

}
