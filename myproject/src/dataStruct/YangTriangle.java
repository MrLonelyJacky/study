package dataStruct;

import java.util.*;

public class YangTriangle {
    public static List<List<Integer>> generate(int numRows) {
        if (numRows ==0){
            return new ArrayList<>();
        }
        List<List<Integer>> lists = new ArrayList<>(numRows);
        List<Integer> one = new ArrayList<>(1);
        one.add(1);
        lists.add(one);
        if (numRows ==1){
            return lists;
        }
        List<Integer> two = new ArrayList<>(2);
        two.add(1);
        two.add(1);
        lists.add(two);
        if (numRows==2){
            return lists;
        }

        List<Integer> previousList = lists.get(1);
        for (int i = 2; i <= numRows-1; i++) {
            int afterCapacity = i + 1;
            List<Integer> after = new ArrayList<>(afterCapacity);
            after.add(1);

            for (int j = 1; j < afterCapacity-1; j++) {
                after.add(previousList.get(j - 1) + previousList.get(j));
            }
            after.add(1);
            lists.add(after);
            previousList = after;
        }
        return lists;
    }

    public static void main(String[] args) {
        List<List<Integer>> generate = generate(5);
        for (List<Integer> item : generate) {
            System.out.println(Arrays.toString(item.toArray()));
        }
    }
}
