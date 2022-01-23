package designModel.stateModel;

/**
 * 出售状态 只有此状态才能发放糖果
 */
public class SoldState implements State {
    GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("wait we are giving gumball now...");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("sorry,you have turned the crank");
    }

    @Override
    public void turnCrank() {
        System.out.println("you have turned the crank");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        } else {
            System.out.println("out of gumballs");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }

    }
}
