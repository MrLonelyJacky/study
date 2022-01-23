package thinkingJava.container;

import java.util.*;

/**
 * Created by 15151 on 2019/3/27.
 */
public class CollectionTest {
    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1,2,3));
        collection.addAll(Arrays.asList(4,5,6));
        Collections.addAll(collection,7,8,9);
        System.out.println(collection);
        Iterator<Integer> iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3));
        ListIterator<Integer> integerListIterator = list.listIterator();
        while (integerListIterator.hasNext()){
            System.out.println(integerListIterator.next());
        }
        while (integerListIterator.hasPrevious()){
            System.out.println(integerListIterator.previous());
            integerListIterator.set(3);
        }
        while (integerListIterator.hasNext()){
            System.out.println(integerListIterator.next());
        }
    }
}
