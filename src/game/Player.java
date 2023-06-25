package game;

import cards.Card;
import cards.Deck;

import java.util.ArrayList;
import java.util.List;
public class Player {
    private String name;
    private int score;
    private boolean isUnoState;
    private List<Card> hand;

    public Player(String name){
        setName(name);
        this.isUnoState = false;
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

    public boolean isUnoState() {
        return isUnoState;
    }

    public void setUnoState(boolean unoState) {
        isUnoState = unoState;
    }

    public List<Card> getHand() {
        return hand;
    }
    public Card drawFromDeck(Deck deck)   {
        System.out.println(getName() + " is drawing from deck...");
        Card card = deck.drawTop();
        hand.add(card);
        this.isUnoState = false;
        return card;
    }
    public void drawNFromDeck(Deck deck, int numToDraw)  {
        while(numToDraw-- > 0){
            drawFromDeck(deck);
        }
    }
    public void addToHand(Card card){
        hand.add(card);
        this.isUnoState = false;
    }
    public Card throwCard(Card card){
        hand.remove(card);
        return card;
    }
    public void shoutUno(){
        System.out.println("UNO!");
    }
}
