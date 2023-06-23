import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Game {
    private int minPlayers;
    private List<Player> players;
    private Player currentPlayer;
    private Player previousPlayer;
    private Deck deck;
    private Card lastDiscardedCard;
    private List<Card> discardPile;
    private DealStrategy dealStrategy;
    private ScoreComputationStrategy scoreComputationStrategy;
    private DeckInitStrategy deckInitStrategy;
    private DiscardPileInitStrategy discardPileInitStrategy;
    private GameDirection direction;
    private String nextPlayableColor;
    private String nextPlayableFaceValue;
    private int currentPlayerIndex;
    private int previousPlayerIndex;
    private Player lastRoundWinner;
    private Player maxScorePlayer;
    private int winnerScore;

    public Game() {
        this.minPlayers = 3;
        this.players = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.deck = new Deck();
        this.dealStrategy = new BasicDealStrategy();
        this.scoreComputationStrategy = new BasicScoreComputationStrategy();
        this.deckInitStrategy = new BasicDeckInitStrategy();
        this.discardPileInitStrategy = new BasicDiscardPileInitStrategy();
        this.direction = GameDirection.CLOCKWISE;
        this.nextPlayableColor = null;
        this.nextPlayableFaceValue = null;
        this.currentPlayerIndex = 0;
        this.previousPlayerIndex = -1;
        this.winnerScore = 500;
    }
    public int getWinnerScore() {
        return winnerScore;
    }

    public void setWinnerScore(int winnerScore) {
        this.winnerScore = winnerScore;
    }
    public Card getLastDiscardedCard() {
        return discardPile.get(discardPile.size() - 1);
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public Player getPreviousPlayer() {
        return players.get(previousPlayerIndex);
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(List<Card> discardPile) {
        this.discardPile = discardPile;
    }


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public void toggleDirection(){
        if(direction.toString().equals("CLOCKWISE")){
            direction = GameDirection.ANTICLOCKWISE;
        }
        else{
            direction = GameDirection.CLOCKWISE;
        }
    }

    public int getPreviousPlayerIndex() {
        return previousPlayerIndex;
    }

    public void nextPlayerTurn(){
        previousPlayerIndex = currentPlayerIndex;
        if(direction.toString().equals("CLOCKWISE")){
            currentPlayerIndex = ++currentPlayerIndex % players.size();
        }
        else{
            currentPlayerIndex = (currentPlayerIndex + (players.size() - 1)) % players.size();
        }
    }
    public void setDirection(String direction)  {
        if(!direction.equalsIgnoreCase("CLOCKWISE") && !direction.equalsIgnoreCase("ANTICLOCKWISE")){
            throw new IllegalArgumentException("Game direction could either be clockwise or anticlockwise");
        }
        this.direction = GameDirection.valueOf(direction.toUpperCase());
    }
    public void setDeck(Deck deck){
        this.deck = deck;
    }

    public String getNextPlayableColor() {
        return nextPlayableColor;
    }

    public void setNextPlayableColor(String nextPlayableColor) {
        this.nextPlayableColor = nextPlayableColor;
    }

    public String getNextPlayableFaceValue() {
        return nextPlayableFaceValue;
    }

    public void setNextPlayableFaceValue(String nextPlayableFaceValue) {
        this.nextPlayableFaceValue = nextPlayableFaceValue;
    }
    private void initializePlayers(){
        Scanner sc = new Scanner(System.in);
        System.out.println("How many players do you want to register in the game?");
        int numOfPlayers = sc.nextInt();
        checkNumOfPlayers(numOfPlayers);
        String name;
        sc.nextLine();
        while(numOfPlayers-- > 0){
            name = sc.nextLine();
            players.add(new Player(name));
        }
    }
    private void checkNumOfPlayers(int numOfPlayers){
        if(numOfPlayers < minPlayers){
            throw new IllegalArgumentException("The number of players should be equal to or greater than " + minPlayers);
        }
    }
    public void setMinNumOfPlayers(int playersRequired) {
        this.minPlayers = playersRequired;
    }

    public void setDealStrategy(DealStrategy dealStrategy){
        this.dealStrategy = dealStrategy;
    }
    public void setScoreComputationStrategy(ScoreComputationStrategy scoreComputationStrategy){this.scoreComputationStrategy = scoreComputationStrategy;}

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public boolean isOngoing(){
        for(Player player : players){
            if(player.getHand().size() == 0){
                lastRoundWinner = player;
                return false;
            }
        }
        return true;
    }
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
    private void displayHand(Player player){
        System.out.println(player.getName() + ", your hand has the following cards:");
        for(Card card : player.getHand()){
            System.out.print(card.getColor() + " " + card.getFaceValue() + ", ");
        }
        System.out.println();
    }

    private List<Card> getPlayableCards(Player player){
        List<Card> playableCards = new ArrayList<>();
        for(Card card : player.getHand()){
            if(card.getColor().equalsIgnoreCase(nextPlayableColor) || card.getFaceValue().equalsIgnoreCase(nextPlayableFaceValue) || card.getColor().equalsIgnoreCase(EnumBasicCardColor.WILD.toString())){
                playableCards.add(card);
            }
        }
        return playableCards;
    }
    private void promptPlayerTurn(Player player){
        System.out.println(getCurrentPlayer().getName() + "'s turn...");
        System.out.println("Top card in discard pile is " + getLastDiscardedCard().getColor() + " " + getLastDiscardedCard().getFaceValue());
        System.out.println("What card would you like to play?");
        List<Card> playableCards = getPlayableCards(player);
        int i = 1;
        for(Card card : playableCards){
            System.out.println(i + "- " + card.getColor() + " " + card.getFaceValue());
            i++;
        }
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        player.playCard(playableCards.get(choice - 1));
        discardPile.add(playableCards.get(choice - 1));
        setNextPlayableColor(playableCards.get(choice-1).getColor());
        setNextPlayableFaceValue(playableCards.get(choice-1).getFaceValue());
    }

    private int maxScore(){
        int maxScore = players.get(0).getScore();
        for(Player player : players){
            if(player.getScore() > maxScore){
                maxScore = player.getScore();
                maxScorePlayer = player;
            }
        }
        return maxScore;
    }

    private void resetDeck(){
        for(Player player : players){
            for(Card card : player.getHand()){
                deck.add(card);
                player.getHand().remove(card);
            }

        }
        for(Card card : discardPile){
            deck.add(card);
            discardPile.remove(card);
        }
        deck.shuffle();
    }

    private void announceFinalWinner(){
        System.out.println("Congrats." + maxScorePlayer.getName() + " won with score " + maxScorePlayer.getScore());
    }

    //Template method
    public final void play() {
        initializePlayers();
        deckInitStrategy.initializeDeck(deck);
        deck.shuffle();
        while(true) {
            dealStrategy.deal(this);
            discardPileInitStrategy.initializeDiscardPile(discardPile, deck);
            setNextPlayableColor(getLastDiscardedCard().getColor());
            setNextPlayableFaceValue(getLastDiscardedCard().getFaceValue());
            while (isOngoing()) {
                while (getPlayableCards(getCurrentPlayer()).size() == 0) {
                    getCurrentPlayer().drawFromDeck(deck);
                }
                displayHand(getCurrentPlayer());
                promptPlayerTurn(getCurrentPlayer());
                nextPlayerTurn();
                // TODO: Try fixing this whole if else block..
                if (getLastDiscardedCard() instanceof AbstractWildCard) {
                    ((AbstractWildCard) getLastDiscardedCard()).performAction(this);
                } else if (getLastDiscardedCard() instanceof AbstractActionCard) {
                    ((AbstractActionCard) getLastDiscardedCard()).performAction(this);
                }
            }
            scoreComputationStrategy.computeScore(players, lastRoundWinner);
            if(maxScore() < winnerScore){
                resetDeck();
            }
            else{
                announceFinalWinner();
                break;
            }
        }
    }
}
