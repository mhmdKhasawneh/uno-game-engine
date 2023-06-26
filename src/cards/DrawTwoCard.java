package cards;
import game.Game;


public final class DrawTwoCard extends AbstractActionCard {
    public DrawTwoCard(String color, String faceValue) {
        super(color, faceValue);
    }
    @Override
    public void performAction(Game game) {
        game.getCurrentPlayerTurn().drawNFromDeck(game.getDeck(), 2);
        game.nextPlayerTurn();
    }
}
