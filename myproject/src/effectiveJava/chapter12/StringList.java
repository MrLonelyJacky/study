package effectiveJava.chapter12;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by 15151 on 2020/3/24.
 * logically speaking the class represents a stringList
 * physically speaking it a double linked list
 * 逻辑上是要表示一个字符串序列 但是物理上却是双向链表
 */
public final class StringList implements Serializable {
    private int size = 0;
    private Entry head = null;

    private static class Entry implements Serializable {
        String data;
        Entry next;
        Entry previous;
    }

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
        int numElements = stream.readInt();
        for (int i = 0; i < numElements; i++) {
            add((String) stream.readObject());
        }
    }


}
