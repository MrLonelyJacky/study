package concurrent.chapter16;

/**
 * Created by 15151 on 2020/5/13.
 */
public class EatNoodleThread extends Thread {
    private final String name;
    private final TableWare leftTool;
    private final TableWare rightTool;

    public EatNoodleThread(String name, TableWare leftTool, TableWare rightTool) {
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    @Override
    public void run() {
        while (true){
            this.eat();
        }
    }

    private void eat() {
        
    }
}
