package strategies;

import cards.Card;
import cards.Deck;

import java.util.List;

public interface DiscardPileInitStrategy {
    void initializeDiscardPile(List<Card> discardPile, Deck deck);
}
