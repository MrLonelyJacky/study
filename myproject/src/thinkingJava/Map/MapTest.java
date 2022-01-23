package thinkingJava.Map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 15151 on 2019/1/30.
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String,Person> map = new HashMap<>();
        Person person = new Person();
        person.setId("a");
        person.setName("a");
        map.put("a",person);
        map.put("b",person);
        map.values().forEach(item -> System.out.println(item.getName()));
        Map<Person,String> map1= new HashMap<>();
        map1.put(person,"a");
        Person person1 = new Person();
        person1.setId("a");
        person1.setName("a");
        map1.put(person1,"b");
        map1.values().forEach(item -> System.out.println(item));
    }
}
