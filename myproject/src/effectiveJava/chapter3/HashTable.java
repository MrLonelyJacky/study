package effectiveJava.chapter3;

/**
 * 对于内部维护了散列桶这样的对象如何拷贝呢？
 * 如果是普通的Object[] 直接调用数组的拷贝方法即可，因为没有链表
 * 数组中是链表则不能直接拷贝，因为拷贝的仅仅是数组中的链表的头部  next还是引用源对象的next
 */
public class HashTable implements Cloneable {
    private Entry[] buckets = new Entry[100];

    /**
     * 这里要用static修饰
     */
    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        public Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * 采用递归的方式拷贝
         * 如果为了不导致栈溢出，可用迭代得方式
         *
         * @return
         */
        Entry deepCopy() {
            /*Entry entry = new Entry(key, value, next);
            while (entry.next != null) {
                entry.next = new Entry(entry.next.key, entry.next.value, entry.next.next);
                entry.next = entry.next.next;
            }*/
            return new Entry(key, value, next == null ? null : next.deepCopy());
        }
    }

    @Override
    public HashTable clone() {
        try {
            HashTable result = (HashTable) super.clone();
            //注意这里不能采用 buckets.clone()方式，因为数组中是链表，它不能把链表也拷贝一份，得我们自己
            //写方法实现一份拷贝
            result.buckets = new Entry[buckets.length];
            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != null) {
                    result.buckets[i] = buckets[i].deepCopy();
                }
            }
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
