public class BasicDeckInitStrategy implements DeckInitStrategy{
    @Override
    public void initializeDeck(Deck deck) {
        int repeats;
        for(EnumBasicCardColor color : EnumBasicCardColor.values()) {
            if(color.equals(EnumBasicCardColor.WILD)){
                continue;
            }
            for(EnumBasicCardFaceValue value : EnumBasicCardFaceValue.values()) {
                if(value.equals(EnumBasicCardFaceValue.WILD) || value.equals(EnumBasicCardFaceValue.WILD_DRAW_FOUR)){
                    continue;
                }
                else if(value.equals(EnumBasicCardFaceValue.ZERO)){
                    repeats = 1;
                }
                else {
                    repeats = 2;
                }
                while(repeats-- > 0){
                    deck.add(new Card(color,value, EnumBasicCardScoreValue.valueOf(value.toString())));
                }
            }
        }
        for(int i=0;i<4;i++){
            deck.add(new Card(EnumBasicCardColor.WILD, EnumBasicCardFaceValue.WILD, EnumBasicCardScoreValue.WILD));
            deck.add(new Card(EnumBasicCardColor.WILD, EnumBasicCardFaceValue.WILD_DRAW_FOUR, EnumBasicCardScoreValue.WILD_DRAW_FOUR));
        }
    }
}
