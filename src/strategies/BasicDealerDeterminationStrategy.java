package strategies;

import cards.Card;
import cards.Deck;
import cards.NumberedCard;
import game.*;

import java.util.List;

public class BasicDealerDeterminationStrategy implements DealerDeterminationStrategy {
    @Override
    public void determineDealer(Game game) {
        System.out.println("Determining dealer...");
        List<Player> players = game.getPlayers();
        Deck deck = game.getDeck();
        int maxScore = -1;
        for(Player player : players){
            Card drawnCard = player.drawFromDeck(deck);
            while(!(drawnCard instanceof NumberedCard)){
                deck.add(deck.getDeckSize()/2, drawnCard);
                player.throwCard(drawnCard);
                drawnCard = player.drawFromDeck(deck);
            }
            if(drawnCard.getScore() > maxScore){
                maxScore = drawnCard.getScore();
                game.setDealer(player);
            }
            player.throwCard(drawnCard);
            deck.add(deck.getDeckSize() / 2, drawnCard);
        }
        System.out.println(game.getDealer().getName() + " will be dealing.");
    }
}
