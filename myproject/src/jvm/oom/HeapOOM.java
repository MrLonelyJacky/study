package jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/5/21.
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }

    }

}
