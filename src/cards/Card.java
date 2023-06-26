package cards;
import game.*;
public abstract class Card {
    private String color;
    private String faceValue;
    private int score;
    public Card(String color, String faceValue) {
        this.color = color.toUpperCase();
        this.faceValue = faceValue.toUpperCase();
    }
    public final String getColor(){
        return color;
    }
    public final String getFaceValue(){
        return  faceValue;
    }
    public final int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public abstract void performAction(Game game);

}
