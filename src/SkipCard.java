public class SkipCard extends AbstractActionCard{
    public SkipCard(String color, String faceValue) {
        super(color, faceValue);
    }

    @Override
    public void performAction(Game game) {
        game.nextPlayerTurn();
    }
}
