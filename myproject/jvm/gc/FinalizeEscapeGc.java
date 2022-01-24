package jvm.gc;

/**
 * Created by 15151 on 2019/5/23.
 */
public class FinalizeEscapeGc {

    public static FinalizeEscapeGc SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes iam still alive");
    }

    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGc.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGc();
        SAVE_HOOK = null;
        System.gc();
        //让主线程等待一会，因为调用finalize等级较低
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("i am die");
        }

        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("i am die");
        }
    }
}
