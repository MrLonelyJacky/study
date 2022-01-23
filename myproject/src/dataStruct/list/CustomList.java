package dataStruct.list;

import java.util.ArrayList;

/**
 * Created by 15151 on 2019/8/30.
 * 自定义list
 */
public class CustomList<E> extends ArrayList<E> {
    private String getElements() {
        return this.toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (E e : this) {
            stringBuilder.append(e).append(",");
        }
        if (stringBuilder.length()> 0){
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
