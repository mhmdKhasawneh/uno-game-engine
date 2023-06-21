import java.util.List;

public interface PlayableCardsStrategy {
    List<Card> getPlayableCards(Player player, Card card);
}
