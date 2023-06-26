package strategies;

import cards.AbstractDeck;
import cards.Card;

import java.util.List;

public interface DiscardPileInitStrategy {
    void initializeDiscardPile(List<Card> discardPile, AbstractDeck abstractDeck);
}
