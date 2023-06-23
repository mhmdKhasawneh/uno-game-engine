public abstract class AbstractActionCard extends Card {
    public AbstractActionCard(String color, String faceValue) {
        super(color, faceValue);
        setScore(20);
    }

    public abstract void performAction(Game game) throws InterruptedException;
}