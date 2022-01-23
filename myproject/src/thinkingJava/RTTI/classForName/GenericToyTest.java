package thinkingJava.RTTI.classForName;

/**
 * Created by 15151 on 2019/4/21.
 */
public class GenericToyTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<FancyToy> fancyToyClass = FancyToy.class;
        FancyToy fancyToy = fancyToyClass.newInstance();
        Class<? super FancyToy> superclass = fancyToyClass.getSuperclass();
        Object object = superclass.newInstance();
    }
}
