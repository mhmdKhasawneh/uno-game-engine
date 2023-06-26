package game;

import cards.AbstractDeck;
import cards.AbstractCard;

import java.util.ArrayList;
import java.util.List;
public class Player {
    private String name;
    private int score;
    private boolean isUnoState;
    private List<AbstractCard> hand;

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

    public List<AbstractCard> getHand() {
        return hand;
    }
    public AbstractCard drawFromDeck(AbstractDeck abstractDeck)   {
        System.out.println(getName() + " is drawing from deck...");
        AbstractCard card = abstractDeck.drawTop();
        hand.add(card);
        this.isUnoState = false;
        return card;
    }
    public void drawNFromDeck(AbstractDeck abstractDeck, int numToDraw)  {
        while(numToDraw-- > 0){
            drawFromDeck(abstractDeck);
        }
    }
    public void addToHand(AbstractCard card){
        hand.add(card);
        this.isUnoState = false;
    }
    public AbstractCard throwCard(AbstractCard card){
        hand.remove(card);
        return card;
    }
    public List<AbstractCard> getPlayableCards(String nextPlayableColor, String nextPlayableFaceValue){
        List<AbstractCard> playableCards = new ArrayList<>();
        for(AbstractCard card : getHand()){
            if(card.getColor().equalsIgnoreCase(nextPlayableColor) || card.getFaceValue().equalsIgnoreCase(nextPlayableFaceValue)
                    || card.getColor().equalsIgnoreCase("WILD")){
                playableCards.add(card);
            }
        }
        return playableCards;
    }
    public void shoutUno(){
        System.out.println("UNO!");
    }
}
