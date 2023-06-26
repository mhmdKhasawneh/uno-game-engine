package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CardColorsList {
    private static final List<String> colors = new ArrayList<>(){{
        add("RED");
        add("GREEN");
        add("BLUE");
        add("YELLOW");
        add("WILD");
    }};

    public static List<String> getColors(){
        return Collections.unmodifiableList(colors);
    }
    public static void addColor(String color){
        colors.add(color.toUpperCase());
    }
    public static boolean doesContain(String color){
        return colors.contains(color.toUpperCase());
    }
}
