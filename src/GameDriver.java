import game.Game;
import game.BasicGame;

public class GameDriver {
    public static void main(String[] args) {
        Game myGame = new BasicGame();
        myGame.play();
    }
}