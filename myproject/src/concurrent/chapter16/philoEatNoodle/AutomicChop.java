package concurrent.chapter16.philoEatNoodle;

/**
 * Created by 15151 on 2020/5/15.
 */
public class AutomicChop {
    private  Chopstick leftChop;
    private  Chopstick rightChop;

    public Chopstick getLeftChop() {
        return leftChop;
    }

    public void setLeftChop(Chopstick leftChop) {
        this.leftChop = leftChop;
    }

    public Chopstick getRightChop() {
        return rightChop;
    }

    public void setRightChop(Chopstick rightChop) {
        this.rightChop = rightChop;
    }
}
