import java.util.Scanner;

public abstract class AbstractWildCard extends Card{
    public AbstractWildCard(String color, String faceValue) {
        super(color, faceValue);
        setScore(50);
    }

    public void changeNextPlayableColor(Game game){
        Scanner sc = new Scanner(System.in);
        System.out.println("What color do you want to change to?");
        String newColor = sc.next().toUpperCase();
        do{
            try{
                EnumBasicCardColor.valueOf(newColor);
                break;
            } catch(IllegalArgumentException e){
                System.out.println("Color chosen is invalid.");
            }
            System.out.println("Enter a valid color");
            newColor = sc.next().toUpperCase();
        }while(true);
        game.setNextPlayableColor(newColor);
        game.setNextPlayableFaceValue(null);
    }
}
