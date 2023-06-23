public abstract class Card {
    private String color;
    private String faceValue;
    private int score;
    public Card(String color, String faceValue) {
        this.color = color;
        this.faceValue = faceValue;
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
}
