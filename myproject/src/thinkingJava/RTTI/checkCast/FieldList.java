package thinkingJava.RTTI.checkCast;

import thinkingJava.RTTI.zcFactory.Part;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/4/21.
 */
 class FieldList<T> extends Part{
    private Class<T> type;

    public FieldList(Class<T> type) {
        this.type = type;
    }

    public List<T> create(int nElements) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < nElements; i++) {
            try {
                result.add(type.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FieldList<CountedInteger> fieldList = new FieldList<>(CountedInteger.class);
        System.out.println(fieldList.create(15));
    }
}
