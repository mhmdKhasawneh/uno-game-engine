package cards;

public abstract class AbstractWildCard extends AbstractCard {
    public AbstractWildCard(String color, String faceValue) {
        super(color, faceValue);
        setScore(50);
    }
}
