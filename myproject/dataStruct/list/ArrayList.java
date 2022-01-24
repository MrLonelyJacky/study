package dataStruct.list;

/**
 * Created by 15151 on 2019/6/21.
 */
public class ArrayList {
    private static final int MAX_SIZE = 1000;
    private int pos = -1;//当前位置
    private Object[] objects = new Object[MAX_SIZE];

    //新增
    public void add(Object data) {
        pos++;
        objects[pos] = data;
    }

    //在指定位置上新增
    public void addByIndex(int index, Object data) {
        if (index < 0 || index > objects.length) {
            return;
        }
        for (int i = pos; i >= index; i--) {
            objects[i + 1] = objects[i];
        }
        objects[index] = data;
        pos++;
    }

    //指定位置删除
    public Object remove(int index) {
        if (index < 0 || index > objects.length) {
            return null;
        }
        Object o = objects[index];
        for (int i = index; i <= pos; i++) {
            objects[i] = objects[i + 1];
        }
        pos--;
        return o;
    }

    //删除最小值并将最后一个元素放置到最小地区
    public Object removeMin() {
        if (pos < 0) {
            return null;
        }
        int minIndex = 0;
        int min = (int) objects[0];
        //找出最小位置
        for (int i = 1; i <= pos; i++) {
            int value = (int) objects[i];
            if (min > value) {
                min = value;
                minIndex = i;
            }
        }
        Object o = objects[minIndex];
        objects[minIndex] = objects[pos];
        pos--;
        return o;
    }

    //交换顺序表  空间复杂度O(1)
    public void revert() {
        for (int i = 0; i <= pos / 2; i++) {
            Object start = objects[i];
            Object end = objects[pos - i];
            objects[i] = end;
            objects[pos - i] = start;
        }
    }

    //删除所有值为x的元素
    public void removeData(Object x) {
        int count = 0;
        for (int i = 0; i <= pos; i++) {
            if (objects[i] == x) {
                count++;//记录出现x的次数
                continue;
            }
            objects[i - count] = objects[i];
        }
        pos = pos - count;
    }



    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(2);
      /*  arrayList.addByIndex(2, 6);
        for (int i = 0; i <= arrayList.pos; i++) {
            System.out.print(arrayList.objects[i] + " ");
        }
        System.out.println();
        arrayList.remove(2);
        for (int i = 0; i <= arrayList.pos; i++) {
            System.out.print(arrayList.objects[i] + " ");
        }
        System.out.println();
        System.out.println(arrayList.removeMin());
        System.out.println();
        for (int i = 0; i <= arrayList.pos; i++) {
            System.out.print(arrayList.objects[i] + " ");
        }
        System.out.println();
        arrayList.revert();
        for (int i = 0; i <= arrayList.pos; i++) {
            System.out.print(arrayList.objects[i] + " ");
        }*/
        arrayList.removeData(2);
        for (int i = 0; i <= arrayList.pos; i++) {
            System.out.print(arrayList.objects[i] + " ");
        }
    }


}
