package strategies;

import cards.*;

public class BasicDeckInitStrategy implements DeckInitStrategy{
    @Override
    public void initializeDeck(Deck deck) {
        for(String color : CardColorsList.getColors()){
            if(color.equals("WILD")){
                break;
            }
            int i = 0;
            for(String value : CardFaceValuesList.getFaceValues()){
                if(i == 13){
                    break;
                }
                if(i == 0){
                    addNof(new NumberedCard(color, value, i), deck,1);
                }
                else if(i >= 10 && i<= 12){
                    if(i == 10){
                        addNof(new ReverseCard(color, value), deck,2);
                    }
                    else if(i == 11){
                        addNof(new SkipCard(color, value), deck,2);
                    }
                    else{
                        addNof(new DrawTwoCard(color, value), deck,2);
                    }
                }
                else{
                    addNof(new NumberedCard(color, value, i), deck,2);
                }
                i++;
            }
        }
            addNof(new WildDrawFourCard("WILD", "WILD_DRAW_FOUR"), deck,4);
            addNof(new WildCard("WILD", "WILD"), deck,4);
    }

    private void addNof(Card card, Deck deck, int n){
        while(n-- > 0){
                deck.add(card);
        }
    }
}
