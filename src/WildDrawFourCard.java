import java.util.Scanner;

public class WildDrawFourCard extends AbstractWildCard{
    public WildDrawFourCard(String color, String faceValue) {
        super(color, faceValue);
    }

    @Override
    public void performAction(Game game) {
        changeNextPlayableColor(game);

        game.getCurrentPlayer().drawNFromDeck(game.getDeck(), 4);
        game.nextPlayerTurn();
    }
}
