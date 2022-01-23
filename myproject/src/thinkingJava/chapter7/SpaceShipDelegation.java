package thinkingJava.chapter7;

/**
 * 飞船委托类
 */
public class SpaceShipDelegation {
    /**
     * 被代理对象
     */
    private SpaceShipControl spaceShipControl = new SpaceShipControl();

    private String name;

    public SpaceShipDelegation(String name) {
        this.name = name;
    }

    /**
     * 委托转发
     * @param step
     */
    public void up(int step){
        spaceShipControl.up(step);
    }

    public void down(int step){
        spaceShipControl.down(step);
    }

    public void left(int step){
        spaceShipControl.left(step);
    }

    public void right(int step){
        spaceShipControl.right(step);
    }
}
