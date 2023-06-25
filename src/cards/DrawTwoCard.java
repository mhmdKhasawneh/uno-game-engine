package cards;
import game.Game;


public class DrawTwoCard extends AbstractActionCard {
    public DrawTwoCard(String color, String faceValue) {
        super(color, faceValue);
    }
    @Override
    public void performAction(Game game) {
        game.getCurrentPlayer().drawNFromDeck(game.getDeck(), 2);
        game.nextPlayerTurn();
    }
}