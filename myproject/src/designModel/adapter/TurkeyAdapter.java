package designModel.adapter;

/**
 *适配器，让turkey伪装成duck
 */
public class TurkeyAdapter implements Duck {

    private final Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    /**
     *模仿鸭子叫
     */
    @Override
    public void quack() {
        turkey.gobble();
    }

    /**
     * turkey的飞行距离太少了，要想和duck一致要五次fly
     */
    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
