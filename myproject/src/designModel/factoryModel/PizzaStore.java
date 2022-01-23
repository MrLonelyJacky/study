package designModel.factoryModel;

/**
 * 抽象工厂类 制作不同地域的pizza 需要 不同类型的工厂
 */
public abstract class PizzaStore {
    Pizza pizza;
    public Pizza orderPizza(){
        //一些伪代码
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    public abstract Pizza createPizza();
}
