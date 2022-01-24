package thinkingJava.container.set;

/**
 * Created by 15151 on 2019/4/1.
 */
public class TreeType extends SetType implements Comparable<TreeType> {
    public TreeType(int i) {
        super(i);
    }

    @Override
    public int compareTo(TreeType o) {
        return o.i < i ? -1 : (o.i == i ? 0 : 1);
    }
}
