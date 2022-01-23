package thinkingJava.innerClass.UpperCastint;

/**
 * Created by 15151 on 2019/4/6.
 */
public class TestParcel {
    public static void main(String[] args) {
        Parcel parcel = new Parcel();
        System.out.println(parcel.getDestination().readLabel() + " is " + parcel.getContents().value() + " city");
    }
}
