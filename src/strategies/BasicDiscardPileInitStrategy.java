package strategies;

import cards.AbstractActionCard;
import cards.AbstractDeck;
import cards.AbstractWildCard;
import cards.Card;

import java.util.ArrayList;
import java.util.List;

public class BasicDiscardPileInitStrategy implements DiscardPileInitStrategy{
    @Override
    public void initializeDiscardPile(List<Card> discardPile, AbstractDeck abstractDeck) {
        List<Card> failedCandidates = new ArrayList<>();
        Card candidate = abstractDeck.drawTop();
        while(candidate instanceof AbstractActionCard || candidate instanceof AbstractWildCard){
            failedCandidates.add(candidate);
            candidate = abstractDeck.drawTop();
        }
        discardPile.add(candidate);
        putBackFailedCandidates(abstractDeck, failedCandidates);
        abstractDeck.shuffle();
    }
    private void putBackFailedCandidates(AbstractDeck abstractDeck, List<Card> failedCandidates){
        for(Card card : failedCandidates){
            abstractDeck.add(card);
        }
    }
}
