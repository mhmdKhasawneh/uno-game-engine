import java.util.Scanner;

public class WildCard extends AbstractWildCard{
    public WildCard(String color, String faceValue) {
        super(color, faceValue);
    }

    @Override
    public void performAction(Game game) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println(game.getCurrentPlayer().getName() + ", what color do you want to change to?");
        game.setNextPlayableColor(sc.nextLine().toUpperCase());
    }
}
