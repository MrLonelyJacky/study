package concurrent.chapter21;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author liuhaibo on 2018/03/06
 */
public class WeakRefDemo {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String... args) {

        // all these objects have a strong reference
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        ThreadLocal<Integer> t = threadLocal;

        // other references to these objects
        Object strongA = a;
        SoftReference<Object> softB = new SoftReference<>(b);
        WeakReference<Object> weakC = new WeakReference<>(c);
        WeakReference<ThreadLocal<Integer>> weakReference = new WeakReference<>(t);
        // free the former strong references to these objects:

        // there is still a strong reference(strongA) to the first object
        a = null;
        // only a soft reference(softB) refers to the second object
        b = null;
        // only a weak reference(weakC) refers to the third object
        c = null;

        threadLocal = null;

        System.out.println("Before gc...");
        System.out.println(String.format("strongA = %s, softB = %s, weakC = %s, weakT = %s", strongA, softB.get(), weakC.get(), weakReference.get()));

        System.out.println("Run GC...");

        System.gc();

        // object with only soft reference will be cleaned only if memory is not enough: 用来做缓存很不错
        // object with only weak reference will be cleaned after a gc operation:
        System.out.println("After gc...");
        System.out.println(String.format("strongA = %s, softB = %s, weakC = %s, weakT = %s", strongA, softB.get(), weakC.get(), weakReference.get()));
    }
}


