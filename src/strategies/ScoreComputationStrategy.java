package strategies;
import game.Player;
import java.util.List;

public interface ScoreComputationStrategy {
    void computeScore(List<Player> player, Player winner);
}
