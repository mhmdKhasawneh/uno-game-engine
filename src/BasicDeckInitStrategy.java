public class BasicDeckInitStrategy implements DeckInitStrategy{
    @Override
    public void initializeDeck(Deck deck) {
        for(EnumBasicCardColor color : EnumBasicCardColor.values()){
            if(color.toString().equals(EnumBasicCardColor.WILD.toString())){
                break;
            }
            int i = 0;
            for(EnumBasicCardFaceValue value : EnumBasicCardFaceValue.values()){
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
            addNof(new WildDrawFourCard(EnumBasicCardColor.WILD.toString(), EnumBasicCardFaceValue.WILD_DRAW_FOUR.toString()), deck,4);
            addNof(new WildCard(EnumBasicCardColor.WILD.toString(), EnumBasicCardFaceValue.WILD.toString()), deck,4);
    }

    public void addNof(Card card, Deck deck, int n){
        while(n-- > 0){
                deck.add(card);
        }
    }
}
