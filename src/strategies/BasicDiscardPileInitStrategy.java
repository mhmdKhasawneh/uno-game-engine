package strategies;

import cards.AbstractActionCard;
import cards.AbstractWildCard;
import cards.Card;
import cards.Deck;

import java.util.ArrayList;
import java.util.List;

public class BasicDiscardPileInitStrategy implements DiscardPileInitStrategy{
    @Override
    public void initializeDiscardPile(List<Card> discardPile, Deck deck) {
        List<Card> failedCandidates = new ArrayList<>();
        Card candidate = deck.drawTop();
        while(candidate instanceof AbstractActionCard || candidate instanceof AbstractWildCard){
            failedCandidates.add(candidate);
            candidate = deck.drawTop();
        }
        discardPile.add(candidate);
        putBackFailedCandidates(deck, failedCandidates);
        deck.shuffle();
    }
    private void putBackFailedCandidates(Deck deck, List<Card> failedCandidates){
        for(Card card : failedCandidates){
            deck.add(card);
        }
    }
}
