package thinkingJava.Reflect.Null;

/**
 * Created by 15151 on 2019/4/27.
 */
public class People {
    public final String first;
    public final String last;
    public final String address;

    People(String first, String last, String address) {
        this.first = first;
        this.last = last;
        this.address = address;
    }

    @Override
    public String toString() {
        return "People{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static class NullPerson extends People implements Null {
        private NullPerson() {
            super("none","none","none");
        }

        @Override
        public String toString() {
            return "NullPerson{}";
        }
    }

    public static final People NULL = new NullPerson();
}
