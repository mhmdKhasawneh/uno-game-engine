package strategies;

import cards.*;

public class BasicDeckInitStrategy implements DeckInitStrategy{
    @Override
    public void initializeDeck(AbstractDeck abstractDeck) {
        for(String color : abstractDeck.getColors()){
            if(color.equals("WILD")){
                break;
            }
            int i = 0;
            for(String value : abstractDeck.getFaceValues()){
                if(i == 13){
                    break;
                }
                if(i == 0){
                    abstractDeck.addN(new NumberedCard(color, value, i),1);
                }
                else if(i >= 10 && i<= 12){
                    if(i == 10){
                        abstractDeck.addN(new ReverseCard(color, value),2);
                    }
                    else if(i == 11){
                        abstractDeck.addN(new SkipCard(color, value),2);
                    }
                    else{
                        abstractDeck.addN(new DrawTwoCard(color, value),2);
                    }
                }
                else{
                    abstractDeck.addN(new NumberedCard(color, value, i),2);
                }
                i++;
            }
        }
            abstractDeck.addN(new WildDrawFourCard("WILD", "WILD_DRAW_FOUR"),4);
            abstractDeck.addN(new WildCard("WILD", "WILD"), 4);
    }
}
