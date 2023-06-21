public class Card {
    IEnumCardColor color;
    IEnumCardFaceValue value;
    IEnumCardScoreValue score;

    public Card(IEnumCardColor color, IEnumCardFaceValue value, IEnumCardScoreValue score) {
        this.color = color;
        this.value = value;
        this.score = score;
    }
    public String getColor(){
        return color.toString();
    }
    public String getFaceValue(){
        return  value.toString();
    }
    public int getCardScore() {
        return score.getCardScore();
    }
}
