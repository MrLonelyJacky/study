package jvm.oom;

/**
 * Created by 15151 on 2019/5/22.
 */
public class RuntimePool {
    public static void main(String[] args) {

        /*List<thinkingJava.String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(thinkingJava.String.valueOf(i++).intern());
        }*/

        String s1= new StringBuilder().append("jsj").append("rj").toString();
        System.out.println(s1 == s1.intern());
        String s2= new StringBuilder().append("ja").append("va").toString();
        System.out.println(s2.intern()==s2);
    }
}
