package effectiveJava.chapter2;

/**
 * Created by 15151 on 2020/3/23.
 * destroy Vulnerable class
 * finalizer attack can avoid parameter safety check
 * because of finalize method even if the object new fail but cannot be collected
 * when gc the object can be used again
 */
public class AttackVulnerable extends Vulnerable {
    static Vulnerable vulnerable;

    AttackVulnerable(int value) {
        super(value);
    }

    @Override
    public void finalize() {
        vulnerable = this;
    }

    public static void main(String[] args) {
        try {
            new AttackVulnerable(-1);
        } catch (Exception e) {
            System.out.println(e);
        }
        //it will relive the object
        System.gc();
        //System.runFinalization();
        if (vulnerable != null) {
            System.out.println("vulnerable object：" + vulnerable + "：created");
        }

    }
}
