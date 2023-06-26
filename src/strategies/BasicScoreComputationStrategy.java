package strategies;
import game.Player;
import cards.AbstractCard;

import java.util.List;

public class BasicScoreComputationStrategy implements ScoreComputationStrategy{
    @Override
    public void computeScore(List<Player> players, Player roundWinner){
        int score = 0;
        for(Player player : players){
            for(AbstractCard card : player.getHand()){
                score += card.getScore();
            }
        }
        roundWinner.setScore(score + roundWinner.getScore());
    }
}
