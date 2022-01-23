package thinkingJava.container.set;

/**
 * Created by 15151 on 2019/4/1.
 */
public class SetType {
    int i;

    public SetType(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
       return o instanceof SetType && i==((SetType) o).i;
    }

    @Override
    public String toString() {
        return Integer.toString(i);
    }
}
