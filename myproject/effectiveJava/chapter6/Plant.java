package effectiveJava.chapter6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 15151 on 2020/1/19.
 * 这是一种烹饪用的香草
 */
public class Plant {
    public Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    enum LifeCycle {ANNUAL, PERENNIAL, BIENNIAL}
    final String name;
    final LifeCycle lifeCycle;

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Set<Plant>[] plants = new Set[LifeCycle.values().length];
        for (int i = 0; i < plants.length; i++) {
            plants[i] = new HashSet<>();
        }
        List<Plant> garden = new ArrayList<>();
        for (Plant p : garden) {

        }
    }
}
