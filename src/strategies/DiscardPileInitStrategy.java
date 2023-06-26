package strategies;

import cards.AbstractDeck;
import cards.AbstractCard;

import java.util.List;

public interface DiscardPileInitStrategy {
    void initializeDiscardPile(List<AbstractCard> discardPile, AbstractDeck abstractDeck);
}
