package cards;
import game.Game;
public class NumberedCard extends Card{
    public NumberedCard(String color, String faceValue, int score) {
        super(color, faceValue);
        setScore(score);
    }

    @Override
    public void performAction(Game game) {
        game.setNextPlayableColor(getColor());
        game.setNextPlayableFaceValue(getFaceValue());
    }
}
