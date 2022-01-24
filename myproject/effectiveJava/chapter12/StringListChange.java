package effectiveJava.chapter12;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by 15151 on 2020/3/24.
 * TODO 自定义序列化形式了解
 */
public class StringListChange implements Serializable {
    private transient int size = 0;
    private transient Entry head = null;

    private static class Entry {
        String data;
        Entry next;
        Entry previous;
    }

    /**
     * appends the specified string to the list
     *
     * @param s
     */
    public final void add(String s) {

    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.write(size);
        for (Entry e = head; e != null; e = e.next) {
            stream.writeObject(e.data);
        }
    }


    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int numElement = stream.readInt();
        for (int i = 0; i < numElement; i++) {
            add((String) stream.readObject());
        }
    }
}
