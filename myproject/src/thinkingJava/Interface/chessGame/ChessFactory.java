package thinkingJava.Interface.chessGame;

/**
 * Created by 15151 on 2019/4/6.
 */
public class ChessFactory implements GameFactory{
    @Override
    public Game getGame() {
        return new Chess();
    }
}
