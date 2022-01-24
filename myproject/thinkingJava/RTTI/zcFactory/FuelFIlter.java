package thinkingJava.RTTI.zcFactory;

/**
 * Created by 15151 on 2019/4/22.
 */
public class FuelFIlter extends Filter {
    /**
     * 将对象的创建过程交给类本身
     */
    public static class Factory implements thinkingJava.RTTI.zcFactory.Factory<FuelFIlter> {
        public FuelFIlter create() {
            return new FuelFIlter();
        }
    }
}
