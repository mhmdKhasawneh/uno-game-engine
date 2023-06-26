package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class AbstractDeck {
    private List<Card> deck;
    private int deckSize;
    private List<String> deckColors;
    private List<String> deckFaceValues;

    public AbstractDeck() {
        deck = new ArrayList<>();
        deckSize = 0;
        deckColors = new ArrayList<>(){{
            add("RED");
            add("GREEN");
            add("BLUE");
            add("YELLOW");
            add("WILD");
        }};
        deckFaceValues = new ArrayList<>(){{
            add("ZERO");
            add("ONE");
            add("TWO");
            add("THREE");
            add("FOUR");
            add("FIVE");
            add("SIX");
            add("SEVEN");
            add("EIGHT");
            add("NINE");
            add("REVERSE");
            add("SKIP");
            add("DRAW_TWO");
            add("WILD_DRAW_FOUR");
            add("WILD");
        }};
    }

    public void add(Card card){
        deck.add(card);
        deckSize++;
    }
    public void add(int index, Card card){
        deck.add(index, card);
        deckSize++;
    }
    public void addN(Card card, int num){
        while(num-- > 0){
            add(card);
        }
    }
    public Card drawTop(){
        deckSize--;
        return deck.remove(deck.size()-1);
    }
    public void addFaceValue(String faceValue){
        deckFaceValues.add(faceValue.toUpperCase());
    }
    public void addColor(String color){
        deckColors.add(color.toUpperCase());
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
    public boolean doesContainColor(String color){
        return deckColors.contains(color.toUpperCase());
    }
    public boolean doesContainFaceValue(String faceValue){
        return deckFaceValues.contains(faceValue.toUpperCase());
    }
    public int getDeckSize() {
        return deckSize;
    }

    public final List<Card> getDeck() {
        return deck;
    }
    public List<String> getColors(){
        return Collections.unmodifiableList(deckColors);
    }
    public List<String> getFaceValues(){
        return Collections.unmodifiableList(deckFaceValues);
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
