public class NumberedCard extends Card{
    public NumberedCard(String color, String faceValue, int score) {
        super(color, faceValue);
        setScore(score);
    }
}
