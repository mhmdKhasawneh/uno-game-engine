package cards;

import java.util.ArrayList;

public class BasicDeck extends AbstractDeck{
    public BasicDeck(){
        setDeckColors(new ArrayList<>(){{
            add("RED");
            add("GREEN");
            add("BLUE");
            add("YELLOW");
            add("WILD");
        }});
        setDeckFaceValues(new ArrayList<>(){{
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
        }});
    }
}
