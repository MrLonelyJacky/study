package designModel.command;

/**
 * 具体的命令
 */
public class ConcreteCommand implements ICommand{
    private final Reciver reciver;

    public ConcreteCommand(Reciver reciver) {
        this.reciver = reciver;
    }

    @Override
    public void execute() {
        reciver.action();
    }
}
