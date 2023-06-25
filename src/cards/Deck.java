package cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> deck;
    private int deckSize;

    public Deck() {
        deck = new ArrayList<>();
        deckSize = 0;
    }

    public int getDeckSize() {
        return deckSize;
    }

    public final List<Card> getDeck() {
        return deck;
    }
    public void add(Card card){
        deck.add(card);
        deckSize++;
    }
    public void add(int index, Card card){
        deck.add(index, card);
        deckSize++;
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
        deckSize--;
        return deck.remove(deck.size()-1);
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
