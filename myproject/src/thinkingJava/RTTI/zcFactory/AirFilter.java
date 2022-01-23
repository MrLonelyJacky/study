package thinkingJava.RTTI.zcFactory;

/**
 * Created by 15151 on 2019/4/22.
 */
public class AirFilter extends Filter{
    public static class Factory implements thinkingJava.RTTI.zcFactory.Factory<AirFilter> {
        public AirFilter create() {
            return new AirFilter();
        }
    }
}
