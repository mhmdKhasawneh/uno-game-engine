package game;

import cards.BasicDeck;
import strategies.BasicDealerDeterminationStrategy;
import strategies.BasicDeckInitStrategy;
import strategies.BasicDiscardPileInitStrategy;
import strategies.BasicScoreComputationStrategy;

public class BasicGame extends Game {
    public BasicGame(){
        setMinPlayers(2);
        setDeck(new BasicDeck());
        setDealerDeterminationStrategy(new BasicDealerDeterminationStrategy());
        setScoreComputationStrategy(new BasicScoreComputationStrategy());
        setDeckInitStrategy(new BasicDeckInitStrategy());
        setDiscardPileInitStrategy(new BasicDiscardPileInitStrategy());
        setGameDirection("CLOCKWISE");
        setWinningScore(500);
        setInitialHandSize(7);
        setMissedUnoDrawPenalty(2);
        setNoPlayableCardPenalty(1);
    }
}
