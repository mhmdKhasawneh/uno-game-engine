package cards;
import game.Game;
public final class ReverseCard extends AbstractActionCard{
    public ReverseCard(String color, String faceValue) {
        super(color, faceValue);
    }
    @Override
    public void performAction(Game game) {
        game.toggleDirection();
        game.nextPlayerTurn();
        game.nextPlayerTurn();
    }
}
