package thinkingJava.RTTI.PetDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/4/21.
 * 通过className方式生成pet
 */
public class FornameCreater extends PetCreater{
    private static List<Class<? extends Pet>> types = new ArrayList<>();
    private static String[] typeNames ={"thinkingJava.RTTI.PetDemo.Cat","thinkingJava.RTTI.PetDemo.Dog","thinkingJava.RTTI.PetDemo.ErHa","thinkingJava.RTTI.PetDemo.Miao"};
    @SuppressWarnings("unchecked")
    private static void loader(){
        for (String name : typeNames){
            try {
                types.add((Class<? extends Pet>) Class.forName(name));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    List<Class<? extends Pet>> types() {
        return types;
    }
    static {
        loader();
    }
}
