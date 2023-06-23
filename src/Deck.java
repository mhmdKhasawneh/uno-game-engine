import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
    }
    public final List<Card> getDeck() {
        return deck;
    }
    public void add(Card card){
        deck.add(card);
    }
    public void shuffle(){
        Random random = new Random();
        for (int i = deck.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Card temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }
    public Card drawTop(){
        Card card = deck.get(deck.size() - 1);
        deck.remove(deck.size()-1);
        return card;
    }

    public void show(){
        int i=0;
        for(Card card : deck){
            System.out.println(card.getColor() + " " + card.getFaceValue() + " " + card.getScore());
            i++;
        }
        System.out.println(i + " total cards");
    }
}
