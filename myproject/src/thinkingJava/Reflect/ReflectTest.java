package thinkingJava.Reflect;

import thinkingJava.Map.Person;

import java.lang.reflect.Field;

/**
 * Created by 15151 on 2019/3/22.
 */
public class ReflectTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        Field[] fields =Person.class.getDeclaredFields();
        person.setName("aa");
        Field[] fields1 = person.getClass().getDeclaredFields();
        for (Field f: fields) {
            System.out.println(f.getType());
        }
        for (Field f: fields1){
            System.out.println(f.getType());
            f.setAccessible(true);
            System.out.println(f.get(person));
        }
        /*Field field = person.getClass().getField("name");
        Class<?> type = field.getType();
        System.out.println(type);*/
    }

}
