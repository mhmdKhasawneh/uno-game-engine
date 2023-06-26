package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CardFaceValuesList {
    private static final List<String> faceValues = new ArrayList<>(){{
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

    public static List<String> getFaceValues(){
        return Collections.unmodifiableList(faceValues);
    }
    public static void addFace(String faceValue){
        faceValues.add(faceValue.toUpperCase());
    }
    public static boolean doesContain(String faceValue){
        return faceValues.contains(faceValue.toUpperCase());
    }
}
