package effectiveJava.chapter2;

/**
 * @author vinci
 * singleton
 */
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();

    private Elvis() {
    }

    public Elvis getInstance() {
        return INSTANCE;
    }
}
