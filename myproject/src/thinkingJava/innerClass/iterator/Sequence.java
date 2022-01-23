package thinkingJava.innerClass.iterator;

/**
 * Created by 15151 on 2019/2/17.
 */
public class Sequence {
    private Object[] items;
    private int next;

    public Sequence(int size) {
        items = new Object[size];
    }

    public void add(Object x) {
        items[next] = x;
        next++;
    }

    private class SequenceSelector implements Selector {
        private int count;

        public Sequence getSequence(){
            return Sequence.this;
        }

        @Override
        public boolean end() {
            return count == items.length;
        }

        @Override
        public void next() {
            if (count < items.length) {
                count++;
            }
        }

        @Override
        public Object current() {
            return items[count];
        }
    }

    public Selector getSelector() {
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(3);
        sequence.add(new Person("jacky"));
        sequence.add(new Person("hanhan"));
        sequence.add(new Person("ninmeng"));
        Selector selector = sequence.getSelector();
        while (!selector.end()) {
            System.out.println(selector.current());
            selector.next();
        }
    }
}
