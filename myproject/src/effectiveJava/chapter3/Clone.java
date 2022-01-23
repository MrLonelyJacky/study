package effectiveJava.chapter3;

import effectiveJava.chapter2.CellStyle;

/**
 * 如果重写clone方法是父类的 并且父类的clone方法用的是构造器
 * 则会出现转换异常尤其当心 clone方法正确书写
 */
public class Clone extends PhoneNumber implements Cloneable {

    public Clone(short areaCode, short prefix, short lineNum) {
        super(areaCode, prefix, lineNum);
    }

    @Override
    public Clone clone() {
        return (Clone) super.clone();
    }

    public static void main(String[] args) {
        short s1 = 1;
        short s2 = 2;
        short s3 = 3;
        Clone clone = new Clone(s1, s2, s3);
        Clone clone1 = clone.clone();
        System.out.println(clone1.getLineNum());
    }
}
