package designModel.stragy;

public class Duck {
    FlyBehavior flyBehavior;

    public void performFly(){
        //委托给behavior
        flyBehavior.fly();
    }
}
