import java.util.Scanner;

public class WildDrawFourCard extends AbstractWildCard implements IPenalty{
    public WildDrawFourCard(String color, String faceValue) {
        super(color, faceValue);
    }

    @Override
    public void performAction(Game game) {
        changeNextPlayableColor(game);

        game.getCurrentPlayer().drawNFromDeck(game.getDeck(), 4);
        game.nextPlayerTurn();
    }

    @Override
    public boolean performPenalty(Game game) {
        Scanner sc = new Scanner(System.in);
        Player previousPlayer = game.getPreviousPlayer();
        Player currentPlayer = game.getCurrentPlayer();
        System.out.println(currentPlayer.getName() + ", would you like to challenge " + previousPlayer.getName() + "? (y,n)");
        String choice = sc.next();
        if(choice.equalsIgnoreCase("y")){
            String playableColor = game.getNextPlayableColor();
            String playableFaceValue = game.getNextPlayableFaceValue();
            boolean illegal = false;
            for(Card card : previousPlayer.getHand()){
                if(card.getColor().equalsIgnoreCase(playableColor) || card.getFaceValue().equalsIgnoreCase(playableFaceValue)){
                    illegal = true;
                    System.out.println(previousPlayer.getName() + " has illegally played draw four. They will draw 4 cards.");
                    previousPlayer.drawNFromDeck(game.getDeck(), 4);
                    break;
                }
            }
            if(!illegal){
                System.out.println(previousPlayer.getName() + " has legally played draw four. Continuing" );
            }
            return illegal;
        }
        return false;
    }
}
