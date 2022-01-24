package thinkingJava.String;

import java.io.PrintStream;
import java.util.Formatter;

/**
 * Created by 15151 on 2019/4/30.
 */
public class Turtle {
    private String name;
    private Formatter formatter;
    private Appendable appendable;
    public Turtle(String name, Formatter formatter) {
        this.name = name;
        this.formatter = formatter;
    }

    public void move(int x, int y) {
        formatter.format("%s the turtle is at (%d,%d)\n", name, x, y);
    }

    public static void main(String[] args) {
        PrintStream outAlias = System.out;

        Turtle turtle = new Turtle("Tommy",new Formatter(outAlias));
        turtle.move(1,2);
        turtle.move(2,3);
        turtle.move(3,2);
    }
}
