package thinkingJava.Interface.chessGame;

/**
 * Created by 15151 on 2019/4/6.
 */
public class Checkers implements Game {
    private int moves = 0;
    private static final int MOVES = 3;

    @Override
    public boolean move() {
        System.out.println("checkers move" + moves);
        return ++moves != MOVES;
    }
}
