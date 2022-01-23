package effectiveJava.chapter9;

/**
 * Created by Administrator on 2020/3/7.
 * use basic types better 1.avoid == operator can take mistakes
 * 2.packing types consumes performance
 * 3.sometimes nullpointException exist because of unpacking
 * because packing types == packing types always false
 * and packing types mix use with basic types can pack or unpack
 *
 * so when we use packing types?
 * 1.for collection key
 * 2.for parametric type
 * 3.for reflect
 */
public class BasicTypesBetter {
    public static void main(String[] args) {
        Integer i = new Integer(3);
        Integer j = new Integer(3);
        if (i==j){
            System.out.printf("==");
        }
        /**
         * the next codes consumes performance because it unpack and pack frequently
         */
        Long sum =0l;
        for (int i1=0;i1<1000;i1++){
            sum=sum+i;
        }
    }
}
