package effectiveJava.chapter2;

/**
 * Created by 15151 on 2020/4/1.
 * factory method
 */
public abstract class PizzaAbstract {
    protected String name;

    public abstract void prepare();

    public void bake() {
        System.out.println(name + "烘培中！");
    }

    public void cut() {
        System.out.println(name + "切割中！");
    }

    public void setName(String name) {
        this.name = name;
    }
}
