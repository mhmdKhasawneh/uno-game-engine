import java.util.Scanner;

public class WildCard extends AbstractWildCard{
    public WildCard(String color, String faceValue) {
        super(color, faceValue);
    }

    @Override
    public void performAction(Game game) {
        changeNextPlayableColor(game);
    }
}
