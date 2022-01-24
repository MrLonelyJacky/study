package thinkingJava.RTTI.zcFactory;

/**
 * Created by 15151 on 2019/4/22.
 */
public class FanBelt extends Belt {
    public static class Factory implements thinkingJava.RTTI.zcFactory.Factory<FanBelt> {
        public FanBelt create() {
            return new FanBelt();
        }
    }
}
