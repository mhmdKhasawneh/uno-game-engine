package strategies;

import cards.AbstractDeck;
import cards.AbstractCard;
import cards.NumberedCard;
import game.*;

import java.util.List;

public class BasicDealerDeterminationStrategy implements DealerDeterminationStrategy {
    @Override
    public void determineDealer(Game game) {
        System.out.println("Determining dealer...");
        List<Player> players = game.getPlayers();
        AbstractDeck abstractDeck = game.getDeck();
        int maxScore = -1;
        for(Player player : players){
            AbstractCard drawnCard = player.drawFromDeck(abstractDeck);
            while(!(drawnCard instanceof NumberedCard)){
                abstractDeck.add(abstractDeck.getDeckSize()/2, drawnCard);
                player.throwCard(drawnCard);
                drawnCard = player.drawFromDeck(abstractDeck);
            }
            if(drawnCard.getScore() > maxScore){
                maxScore = drawnCard.getScore();
                game.setDealer(player);
            }
            player.throwCard(drawnCard);
            abstractDeck.add(abstractDeck.getDeckSize() / 2, drawnCard);
        }
        System.out.println(game.getDealer().getName() + " will be dealing.");
    }
}
