package jvm.monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/6/1.
 */
public class MonitorTest {
    static class OOMObject {
        public byte[] placeHolder = new byte[64 * 1024];
    }

    public static void fillHeap() {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(50);
                list.add(new OOMObject());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.gc();
    }

    public static void main(String[] args) {
        fillHeap();
    }
}
