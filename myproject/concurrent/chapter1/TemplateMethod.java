package concurrent.chapter1;

/**
 * Created by 15151 on 2020/3/25.
 * thread 中的 start and run method use template method like this
 * print method like start  and wrapperPrint like run
 */
public class TemplateMethod {
    /**
     * can not be changed
     * @param message
     */
    public final void print(String message) {
        System.out.println("#########");
        wrapperPrint(message);
        System.out.println("#########");
    }

    /**
     * for changed
     * @param message
     */
    protected void wrapperPrint(String message) {

    }

    public static void main(String[] args) {
        TemplateMethod method1 = new TemplateMethod() {
            @Override
            public void wrapperPrint(String message) {
                System.out.println(message);
            }
        };
        //why the start method is not designed to final??
        Thread thread = new Thread(){
            @Override
            public void run(){
                System.out.println("run");
            }
            @Override
            public  synchronized void start(){
                System.out.println("start");
            }
        };
        thread.start();
        method1.print("hello");
    }
}
