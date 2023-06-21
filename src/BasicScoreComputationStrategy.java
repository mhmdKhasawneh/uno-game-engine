import java.util.List;

public class BasicScoreComputationStrategy implements ScoreComputationStrategy{
    @Override
    public void computeScore(List<Player> players){
        for(Player player : players){
            int score = 0;
            for(Card card : player.getHand()){
                score += card.getCardScore();
            }
            player.setScore(score);
        }
    }
}
