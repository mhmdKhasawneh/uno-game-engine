package cards;
import game.Game;
import java.util.Scanner;

public abstract class AbstractWildCard extends Card{
    public AbstractWildCard(String color, String faceValue) {
        super(color, faceValue);
        setScore(50);
    }
}
