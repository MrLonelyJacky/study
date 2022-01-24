package thinkingJava.innerClass.UpperCastint;

/**
 * Created by 15151 on 2019/4/6.
 */
public class Parcel2 {

    public Destination getDesitination(String s) {

        class PDesitination implements Destination {
            private String label;

            private PDesitination(String label) {
                this.label = label;
            }

            @Override
            public String readLabel() {
                return label;
            }
        }
        return new PDesitination(s);
    }

    public static void main(String[] args) {
        Parcel2 parcel2 = new Parcel2();
        Destination jacky = parcel2.getDesitination("jacky");
        System.out.println(jacky.readLabel());
    }

}
