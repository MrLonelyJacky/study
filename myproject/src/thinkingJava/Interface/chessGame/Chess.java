package thinkingJava.Interface.chessGame;

/**
 * Created by 15151 on 2019/4/6.
 */
public class Chess implements Game {
    private int moves = 0;
    private static final int MOVES = 4;

    @Override
    public boolean move() {
        System.out.println("chess move" + moves);
        return ++moves != MOVES;
    }
}
