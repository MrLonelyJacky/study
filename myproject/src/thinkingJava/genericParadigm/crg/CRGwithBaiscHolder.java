package thinkingJava.genericParadigm.crg;

/**
 * Created by 15151 on 2019/5/5.
 */
public class CRGwithBaiscHolder {
    public static void main(String[] args) {
        SubType subType = new SubType();
        SubType subType2 = new SubType();
        subType.set(subType2);
        SubType subType1 = subType.get();
        subType.f();
    }
}
