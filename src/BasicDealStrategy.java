import java.util.List;

public class BasicDealStrategy implements DealStrategy {
    @Override
    public void deal(Game game) {
        List<Player> players = game.getPlayers();
        Deck deck = game.getDeck();
        for(Player player : players){
            for(int i=0;i<7;i++){
                player.addToHand(deck.drawTop());
            }
        }
        game.setCurrentPlayerIndex(0);
        game.getDiscardPile().add(game.getDeck().drawTop());
    }
}
