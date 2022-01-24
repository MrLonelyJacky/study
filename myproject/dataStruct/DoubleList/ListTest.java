package dataStruct.DoubleList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/3/8.
 */
public class ListTest {
    public static void main(String[] args) {
        String[] strings ={"1","2","3"};

        List<String> list =new ArrayList<>();
        list.forEach(item -> item.hashCode());

    }
}
