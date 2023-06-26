package cards;

public abstract class AbstractActionCard extends AbstractCard {
    public AbstractActionCard(String color, String faceValue) {
        super(color, faceValue);
        setScore(20);
    }

}
