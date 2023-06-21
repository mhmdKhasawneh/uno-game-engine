import java.util.Scanner;

public class BasicActionStrategy implements ActionStrategy{
    @Override
    public void performAction(Game game) throws InterruptedException {
        Player player = game.getPlayers().get(game.getCurrentPlayerIndex());
        Card topDiscardPile = game.getDiscardPile().get(game.getDiscardPile().size() - 1);
        Deck deck = game.getDeck();

        if(topDiscardPile.getFaceValue().equalsIgnoreCase(EnumBasicCardFaceValue.DRAW_TWO.toString())){
            game.nextPlayerTurn();
            player.drawNFromDeck(deck, 2);
            game.nextPlayerTurn();
        }
        else if(topDiscardPile.getFaceValue().equalsIgnoreCase(EnumBasicCardFaceValue.WILD_DRAW_FOUR.toString())){
            game.nextPlayerTurn();
            player.drawNFromDeck(deck, 4);
            game.nextPlayerTurn();
            System.out.println(player.getName() + ", what color do you want to change to?");
            Scanner sc = new Scanner(System.in);
            game.setNextPlayableColor(EnumBasicCardColor.valueOf(sc.nextLine().toUpperCase()));
        }
        else if(topDiscardPile.getFaceValue().equalsIgnoreCase(EnumBasicCardFaceValue.SKIP.toString())){
            game.nextPlayerTurn();
            game.nextPlayerTurn();
        }
        else if(topDiscardPile.getFaceValue().equalsIgnoreCase(EnumBasicCardFaceValue.REVERSE.toString())){
            game.toggleDirection();
            game.nextPlayerTurn();
        }
        else{
            game.nextPlayerTurn();
        }
    }
}
