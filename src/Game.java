import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

public abstract class Game extends Observable {
    private int minPlayers;
    private List<Player> players;
    private Player currentPlayer;
    private Player previousPlayer;
    private Deck deck;
    private Card lastDiscardedCard;

    public Card getLastDiscardedCard() {
        return discardPile.get(discardPile.size() - 1);
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public Player getPreviousPlayer() {
        return players.get(previousPlayerIndex);
    }

    private List<Card> discardPile;
    private DealStrategy dealStrategy;
    private ScoreComputationStrategy scoreComputationStrategy;
    private DeckInitStrategy deckInitStrategy;
    private DiscardPileInitStrategy discardPileInitStrategy;
    private ActionStrategy actionStrategy;
    private GameDirection direction;
    private IEnumCardColor nextPlayableColor;
    private IEnumCardFaceValue nextPlayableFaceValue;

    public Deck getDeck() {
        return deck;
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(List<Card> discardPile) {
        this.discardPile = discardPile;
    }

    private int currentPlayerIndex;
    private int previousPlayerIndex;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Game() {
        this.minPlayers = 3;
        this.players = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.deck = new Deck();
        this.dealStrategy = new BasicDealStrategy();
        this.scoreComputationStrategy = new BasicScoreComputationStrategy();
        this.deckInitStrategy = new BasicDeckInitStrategy();
        this.discardPileInitStrategy = new BasicDiscardPileInitStrategy();
        this.actionStrategy = new BasicActionStrategy();
        this.direction = GameDirection.CLOCKWISE;
        this.nextPlayableColor = null;
        this.nextPlayableFaceValue = null;
        this.currentPlayerIndex = 0;
        this.previousPlayerIndex = -1;
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

    public IEnumCardColor getNextPlayableColor() {
        return nextPlayableColor;
    }

    public void setNextPlayableColor(IEnumCardColor nextPlayableColor) {
        this.nextPlayableColor = nextPlayableColor;
    }

    public IEnumCardFaceValue getNextPlayableFaceValue() {
        return nextPlayableFaceValue;
    }

    public void setNextPlayableFaceValue(IEnumCardFaceValue nextPlayableFaceValue) {
        this.nextPlayableFaceValue = nextPlayableFaceValue;
    }

    private void setObservers(){
        for(Player player : players){
            addObserver(player);
        }
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
        setObservers();
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
            if(card.getColor().equalsIgnoreCase(nextPlayableColor.toString()) || card.getFaceValue().equalsIgnoreCase(nextPlayableFaceValue.toString())){
                playableCards.add(card);
            }
        }
        return playableCards;
    }
    private void promptPlayerTurn(Player player){
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
        setNextPlayableColor(playableCards.get(choice-1).color);
        setNextPlayableFaceValue(playableCards.get(choice-1).value);
    }

    //Template method
    public final void play() throws InterruptedException {
        initializePlayers();
        deckInitStrategy.initializeDeck(deck);
        deck.shuffle();
        dealStrategy.deal(this);
        discardPileInitStrategy.initializeDiscardPile(discardPile, deck);
        setNextPlayableColor(getLastDiscardedCard().color);
        setNextPlayableFaceValue(getLastDiscardedCard().value);
        while(isOngoing()) {
            actionStrategy.performAction(this); //TODO: Think of a way to divide the actions into different components.
            System.out.println(getCurrentPlayer().getName() + "'s turn...");
            while (getPlayableCards(getCurrentPlayer()).size() == 0) {
                getCurrentPlayer().drawFromDeck(deck);
            }
            displayHand(getCurrentPlayer());
            promptPlayerTurn(getCurrentPlayer());
        }
        scoreComputationStrategy.computeScore(players);
    }
}