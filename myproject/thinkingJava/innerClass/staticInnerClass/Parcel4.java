package thinkingJava.innerClass.staticInnerClass;

import thinkingJava.innerClass.UpperCastint.Contents;
import thinkingJava.innerClass.UpperCastint.Destination;

/**
 * Created by 15151 on 2019/4/8.
 * 嵌套类中可以包含静态变量，但不能访问外部类的非静态变量
 */
public class Parcel4 {


    private static class ParcelContents implements Contents {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    protected static class ParcelDesitination implements Destination {
        private String label;

        public ParcelDesitination(String label) {
            this.label = label;
        }

        @Override
        public String readLabel() {
            return label;
        }

        static int x = 10;
    }

    public static void main(String[] args) {
        //静态内部类的使用
        Parcel4.ParcelContents  parcelContents = new ParcelContents();
    }

    public void sqy() {
        int a;
    }

    public static void haha(){
        int a;
    }
}
