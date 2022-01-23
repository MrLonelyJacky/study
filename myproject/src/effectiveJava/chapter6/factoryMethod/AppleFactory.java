package effectiveJava.chapter6.factoryMethod;

/**
 * Created by 15151 on 2020/4/3.
 */
public class AppleFactory implements FruitFactory{
    @Override
    public Fruit getFruit() {
        return new Apple();
    }
}
