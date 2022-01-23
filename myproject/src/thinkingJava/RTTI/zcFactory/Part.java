package thinkingJava.RTTI.zcFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 15151 on 2019/4/22.
 */
public class Part {
    static List<Factory<? extends Part>> partFactories = new ArrayList<>();

    static {
        partFactories.add(new FuelFIlter.Factory());
        partFactories.add(new CabinAirFilter.Factory());
        partFactories.add(new AirFilter.Factory());
        partFactories.add(new FanBelt.Factory());

    }

    private static Random random = new Random();

    public static Part createRandom(){
        int n = random.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }

}
