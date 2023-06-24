import java.util.List;

public class BasicDealerDeterminationStrategy implements DealerDeterminationStrategy {
    @Override
    public void determineDealer(Game game) {
        System.out.println("Determining dealer...");
        List<Player> players = game.getPlayers();
        Deck deck = game.getDeck();
        int maxScore = 0;
        for(Player player : players){
            Card drawnCard = player.drawFromDeck(deck);
            while(!(drawnCard instanceof NumberedCard)){
                deck.add(deck.getDeckSize()/2, drawnCard);
                drawnCard = player.drawFromDeck(deck);
            }
            if(drawnCard.getScore() > maxScore){
                maxScore = drawnCard.getScore();
                game.setDealer(player);
            }
        }
        System.out.println(game.getDealer().getName() + " will be dealing.");
    }
}
