package concurrent.chapter16;

/**
 * Created by 15151 on 2020/5/14.
 */
public class EatNoodelThread extends Thread {
    private final String name;
    private final TableWare leftTool;
    private final TableWare rightTool;

    public EatNoodelThread(String name, TableWare leftTool, TableWare rightTool) {
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    @Override
    public void run() {
        while (true) {
            eat();
        }
    }

    private void eat() {
        synchronized (leftTool) {
            System.out.println(name + "take up" + leftTool);
            synchronized (rightTool) {
                System.out.println(name + "take up" + rightTool);
                System.out.println(name + "is eating");
                System.out.println(name + "take up" + rightTool);
            }
        }
        System.out.println("ok");
    }
}
