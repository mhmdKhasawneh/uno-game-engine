import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Player {
    private String name;
    private int score;
    private List<Card> hand;

    public Player(String name){
        setName(name);
        this.hand = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setName(String name) {
        if(name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }
    public List<Card> getHand() {
        return hand;
    }
    public void drawFromDeck(Deck deck) throws InterruptedException {
        System.out.println("Drawing a card...");
        Thread.sleep(1000);
        hand.add(deck.drawTop());
    }
    public void drawNFromDeck(Deck deck, int numToDraw) throws InterruptedException {
        while(numToDraw-- > 0){
            drawFromDeck(deck);
        }
    }
    public Card playCard(Card card){
        hand.remove(card);
        return card;
    }
    public void addToHand(Card card){
        hand.add(card);
    }
}
