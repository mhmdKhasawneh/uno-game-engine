package strategies;
import game.Player;
import cards.Card;

import java.util.List;

public class BasicScoreComputationStrategy implements ScoreComputationStrategy{
    @Override
    public void computeScore(List<Player> players, Player winner){
        int score = 0;
        for(Player player : players){
            for(Card card : player.getHand()){
                score += card.getScore();
            }
        }
        winner.setScore(score + winner.getScore());
    }
}
