package cards;
import game.Game;
public class WildCard extends AbstractWildCard{
    public WildCard(String color, String faceValue) {
        super(color, faceValue);
    }

    @Override
    public void performAction(Game game) {
        game.promptUserToChangeColor();
    }
}
