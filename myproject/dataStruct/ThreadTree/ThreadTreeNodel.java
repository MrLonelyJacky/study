package dataStruct.ThreadTree;

/**
 * Created by 15151 on 2019/2/2.
 */
public class ThreadTreeNodel<T> {
    /**
     * 这里为了方便操作不私有化
     */
    String data;
    ThreadTreeNodel left;
    ThreadTreeNodel right;
    boolean isLeftThread;
    boolean isRightThread;
}
