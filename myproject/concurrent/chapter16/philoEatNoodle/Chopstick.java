package concurrent.chapter16.philoEatNoodle;

/**
 * Created by 15151 on 2020/5/15.
 * 筷子类
 */
public final class Chopstick {
    private final String name;

    public Chopstick(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
