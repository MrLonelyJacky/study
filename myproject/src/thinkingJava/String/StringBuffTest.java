package thinkingJava.String;

/**
 * Created by 15151 on 2019/2/13.
 */
public class StringBuffTest {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("a");
        System.out.println(stringBuffer.hashCode());
        StringBuffer stringBuffer1 = new StringBuffer("a");
        System.out.println(stringBuffer1.hashCode());
        StringBuffer a = new StringBuffer("1");
        StringBuffer b = new StringBuffer("2");
        change(a,b);//
        System.out.println(a+":"+b);
    }
    public static void change(StringBuffer a,StringBuffer b){
        StringBuffer flag = new StringBuffer(a);
        b = a;
        a = flag;
    }

}
