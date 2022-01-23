package thinkingJava.RTTI.PetDemo;

/**
 * Created by 15151 on 2019/4/21.
 */
public class ErHa extends Dog{
    private ErHa() {
    }

    public ErHa(String name) {
        super(name);
    }

    static class Factory implements thinkingJava.RTTI.zcFactory.Factory<ErHa>{

        @Override
        public ErHa create() {
            return new ErHa();
        }
    }
}
