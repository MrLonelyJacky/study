package thinkingJava.innerClass.UpperCastint;

/**
 * Created by 15151 on 2019/4/6.
 */
public class Parcel {

    private class PDestination implements Destination {
        private String label;

        public PDestination(String label) {
            this.label = label;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }

    private class PContents implements Contents {
        private int value;

        public PContents(int value) {
            this.value = value;
        }

        @Override
        public int value() {
            return value;
        }
    }

    public Destination getDestination() {
        return new Parcel.PDestination("chang zhou");
    }

    public Contents getContents() {
        return new PContents(3);
    }
}
