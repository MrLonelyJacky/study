package designModel.command;

/**
 * 命令请求者
 */
public class Invoker {
    private ICommand iCommand;

    public Invoker(ICommand iCommand) {
        this.iCommand = iCommand;
    }

    public void action() {
        iCommand.execute();
    }


    public static void main(String[] args) {
        ICommand command = new ConcreteCommand(new Reciver());
        Invoker invoker = new Invoker(command);
        invoker.action();
    }
}

