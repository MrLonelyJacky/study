package thinkingJava.Interface.chessGame;

/**
 * Created by 15151 on 2019/4/6.
 */
public class Games {
    public static void play(GameFactory gameFactory) {
        Game game = gameFactory.getGame();
        while (game.move()) {

        }
    }

    public static void main(String[] args) {
        play(new CheckerFactory());
        play(new ChessFactory());
    }
}
