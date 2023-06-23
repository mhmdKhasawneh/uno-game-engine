import java.util.Scanner;

public class WildDrawFourCard extends AbstractWildCard{
    public WildDrawFourCard(String color, String faceValue) {
        super(color, faceValue);
    }

    @Override
    public void performAction(Game game) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println(game.getCurrentPlayer().getName() + ", what color do you want to change to?");
        game.setNextPlayableColor(sc.nextLine().toUpperCase());

        game.nextPlayerTurn();
        game.getCurrentPlayer().drawNFromDeck(game.getDeck(), 4);
        game.nextPlayerTurn();
    }
}
