package thinkingJava.RTTI.zcFactory;

/**
 * Created by 15151 on 2019/4/22.
 */
public class CabinAirFilter extends Filter{
    public static class Factory implements thinkingJava.RTTI.zcFactory.Factory<CabinAirFilter> {
        public CabinAirFilter create() {
            return new CabinAirFilter();
        }
    }
}
