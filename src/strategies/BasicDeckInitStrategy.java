package strategies;

import cards.*;

public class BasicDeckInitStrategy implements DeckInitStrategy{
    @Override
    public void initializeDeck(Deck deck) {
        for(BasicEnumCardColor color : BasicEnumCardColor.values()){
            if(color.toString().equals(BasicEnumCardColor.WILD.toString())){
                break;
            }
            int i = 0;
            for(BasicEnumCardFaceValue value : BasicEnumCardFaceValue.values()){
                if(i == 13){
                    break;
                }
                if(i == 0){
                    addNof(new NumberedCard(color.toString(), value.toString(), i), deck,1);
                }
                else if(i >= 10 && i<= 12){
                    if(i == 10){
                        addNof(new ReverseCard(color.toString(), value.toString()), deck,2);
                    }
                    else if(i == 11){
                        addNof(new SkipCard(color.toString(), value.toString()), deck,2);
                    }
                    else{
                        addNof(new DrawTwoCard(color.toString(), value.toString()), deck,2);
                    }
                }
                else{
                    addNof(new NumberedCard(color.toString(), value.toString(), i), deck,2);
                }
                i++;
            }
        }
            addNof(new WildDrawFourCard(BasicEnumCardColor.WILD.toString(), BasicEnumCardFaceValue.WILD_DRAW_FOUR.toString()), deck,4);
            addNof(new WildCard(BasicEnumCardColor.WILD.toString(), BasicEnumCardFaceValue.WILD.toString()), deck,4);
    }

    private void addNof(Card card, Deck deck, int n){
        while(n-- > 0){
                deck.add(card);
        }
    }
}
