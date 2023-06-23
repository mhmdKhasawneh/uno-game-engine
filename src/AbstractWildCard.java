public abstract class AbstractWildCard extends Card{
    public AbstractWildCard(String color, String faceValue) {
        super(color, faceValue);
        setScore(50);
    }

    public abstract void performAction(Game game) throws InterruptedException;
}
