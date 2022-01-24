package designModel.stateModel;

/**
 * state interface
 */
public interface State {
    /**
     * 塞入25分
     */
    void insertQuarter();

    /**
     * 退出25分
     */
    void ejectQuarter();

    /**
     * 旋转曲柄
     */
    void turnCrank();

    /**
     * 发放糖果
     */
    void dispense();
}
