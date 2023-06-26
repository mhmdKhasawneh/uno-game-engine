package strategies;

import cards.AbstractActionCard;
import cards.AbstractDeck;
import cards.AbstractWildCard;
import cards.AbstractCard;

import java.util.ArrayList;
import java.util.List;

public class BasicDiscardPileInitStrategy implements DiscardPileInitStrategy{
    @Override
    public void initializeDiscardPile(List<AbstractCard> discardPile, AbstractDeck abstractDeck) {
        List<AbstractCard> failedCandidates = new ArrayList<>();
        AbstractCard candidate = abstractDeck.drawTop();
        while(candidate instanceof AbstractActionCard || candidate instanceof AbstractWildCard){
            failedCandidates.add(candidate);
            candidate = abstractDeck.drawTop();
        }
        discardPile.add(candidate);
        putBackFailedCandidates(abstractDeck, failedCandidates);
        abstractDeck.shuffle();
    }
    private void putBackFailedCandidates(AbstractDeck deck, List<AbstractCard> failedCandidates){
        for(AbstractCard card : failedCandidates){
            deck.add(card);
        }
    }
}
