package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class AbstractDeck {
    private List<AbstractCard> deck;
    private int deckSize;
    private List<String> deckColors;
    private List<String> deckFaceValues;

    public AbstractDeck() {
        deck = new ArrayList<>();
        deckColors = new ArrayList<>();
        deckFaceValues = new ArrayList<>();
        deckSize = 0;
    }

    public void add(AbstractCard card){
        deck.add(card);
        deckSize++;
    }
    public void add(int index, AbstractCard card){
        deck.add(index, card);
        deckSize++;
    }
    public void addN(AbstractCard card, int num){
        while(num-- > 0){
            add(card);
        }
    }
    public AbstractCard drawTop(){
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
            AbstractCard temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }

    public void setDeckColors(List<String> deckColors) {
        this.deckColors = deckColors;
    }

    public void setDeckFaceValues(List<String> deckFaceValues) {
        this.deckFaceValues = deckFaceValues;
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

    public final List<AbstractCard> getDeck() {
        return deck;
    }
    public List<String> getColors(){
        return deckColors;
    }
    public List<String> getFaceValues(){
        return deckFaceValues;
    }
    public void show(){
        int i=0;
        for(AbstractCard card : deck){
            System.out.println(card.getColor() + " " + card.getFaceValue() + " " + card.getScore());
            i++;
        }
        System.out.println(i + " total cards");
    }
}
