package cards;
import game.*;
import java.util.Scanner;

public final class WildDrawFourCard extends AbstractWildCard implements IPenalty{
    public WildDrawFourCard(String color, String faceValue) {
        super(color, faceValue);
    }

    @Override
    public void performAction(Game game) {
        game.promptUserToChangeColor();

        game.getCurrentPlayerTurn().drawNFromDeck(game.getDeck(), 4);
        game.nextPlayerTurn();
    }

    @Override
    public void performPenalty(Game game) {
        Scanner sc = new Scanner(System.in);
        Player previousPlayer = game.getPreviousPlayer();
        Player currentPlayer = game.getCurrentPlayerTurn();
        AbstractDeck abstractDeck = game.getDeck();
        System.out.println(currentPlayer.getName() + ", would you like to challenge " + previousPlayer.getName() + "? (y,n)");
        String choice = sc.next();
        if (choice.equalsIgnoreCase("y")) {
            String playableColor = game.getNextPlayableColor();
            String playableFaceValue = game.getNextPlayableFaceValue();
            boolean illegal = false;
            for (AbstractCard card : previousPlayer.getHand()) {
                if (card.getColor().equalsIgnoreCase(playableColor) || card.getFaceValue().equalsIgnoreCase(playableFaceValue)) {
                    illegal = true;
                    System.out.println(previousPlayer.getName() + " has illegally played draw four. They will draw 4 cards.");
                    previousPlayer.drawNFromDeck(abstractDeck, 4);
                    break;
                }
            }
            if (!illegal) {
                System.out.println(previousPlayer.getName() + " has legally played draw four. " + currentPlayer.getName() +
                        " will draw double the cards.");
                currentPlayer.drawNFromDeck(abstractDeck, 8);
            }
            game.promptUserToChangeColor();
        } else {
            performAction(game);
        }
    }
}
