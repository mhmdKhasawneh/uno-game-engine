package strategies;
import game.Player;
import cards.Card;

import java.util.List;

public class BasicScoreComputationStrategy implements ScoreComputationStrategy{
    @Override
    public void computeScore(List<Player> players, Player roundWinner){
        int score = 0;
        for(Player player : players){
            for(Card card : player.getHand()){
                score += card.getScore();
            }
        }
        roundWinner.setScore(score + roundWinner.getScore());
    }
}
