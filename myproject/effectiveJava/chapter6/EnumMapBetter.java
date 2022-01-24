package effectiveJava.chapter6;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by 15151 on 2020/1/24.
 */
public class EnumMapBetter {
    public static void main(String[] args) {
        Map<Plant.LifeCycle, Set<Plant>> map = new EnumMap<>(Plant.LifeCycle.class);
        for (Plant.LifeCycle lifeCycle : Plant.LifeCycle.values()) {
            map.put(lifeCycle, new HashSet<>());
        }
        Set<Plant> set = new HashSet<>();
        for (Plant plant : set) {
            map.get(plant.lifeCycle).add(plant);
        }
    }
}
