package cards;
import game.Game;
public final class WildCard extends AbstractWildCard{
    public WildCard(String color, String faceValue) {
        super(color, faceValue);
    }

    @Override
    public void performAction(Game game) {
        game.promptUserToChangeColor();
    }
}
