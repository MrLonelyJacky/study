package thinkingJava.innerClass.anonymous;

import thinkingJava.innerClass.UpperCastint.Destination;
import thinkingJava.innerClass.UpperCastint.Parcel2;

/**
 * Created by 15151 on 2019/4/7.
 */
public class Parcel3 {
    public Wrapping wrapping(int x) {
        return new Wrapping(x) {
            public int value() {
                return super.value();
            }
        };
    }
    //jdk 1.8会自动将s转为final，为了适应不同的版本还是加上final最好
    public Destination destination(String s){
        final Parcel2 parcel2 = new Parcel2();
        return new Destination() {
            public void print(){
                System.out.println(parcel2.getDesitination(s));
            }
            private String label =s;
            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {

    }
}
