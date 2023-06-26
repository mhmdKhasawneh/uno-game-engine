package game;

import cards.*;
import strategies.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class Game {
    private int minPlayers;
    private int currentPlayerIndex;
    private int winningScore;
    private int initialHandSize;
    private int missedUnoDrawPenalty;
    private int noPlayableCardPenalty;
    private String nextPlayableColor;
    private String nextPlayableFaceValue;
    private List<Player> players;
    private Player currentPlayer;
    private Player previousPlayer;
    private Player roundWinner;
    private Player maxScorePlayer;
    private Player dealer;
    private List<AbstractCard> discardPile;
    private AbstractDeck deck;
    private GameDirection direction;
    private DealerDeterminationStrategy dealerDeterminationStrategy;
    private ScoreComputationStrategy scoreComputationStrategy;
    private DeckInitStrategy deckInitStrategy;
    private DiscardPileInitStrategy discardPileInitStrategy;

    public Game() {
        this.minPlayers = 2;
        this.players = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.deck = new BasicDeck();
        this.dealerDeterminationStrategy = new BasicDealerDeterminationStrategy();
        this.scoreComputationStrategy = new BasicScoreComputationStrategy();
        this.deckInitStrategy = new BasicDeckInitStrategy();
        this.discardPileInitStrategy = new BasicDiscardPileInitStrategy();
        this.direction = GameDirection.CLOCKWISE;
        this.currentPlayerIndex = -1;
        this.winningScore = 500;
        this.initialHandSize = 7;
        this.missedUnoDrawPenalty = 2;
        this.noPlayableCardPenalty = 1;
    }
    public final void play() {
        initializePlayers();
        deckInitStrategy.initializeDeck(deck);
        deck.shuffle();
        dealerDeterminationStrategy.determineDealer(this);
        do{
            deal();
            setCurrentPlayer(players.get(players.indexOf(dealer)));
            nextPlayerTurn();
            discardPileInitStrategy.initializeDiscardPile(discardPile, deck);
            boolean willPerformAction = true;
            while (isOngoing()) {
                if(willPerformAction) {
                    getLastDiscardedCard().performAction(this);
                }
                while (getCurrentPlayerTurn().getPlayableCards(nextPlayableColor, nextPlayableFaceValue).size() == 0) {
                    getCurrentPlayerTurn().drawNFromDeck(deck, noPlayableCardPenalty);
                }
                displayHand(getCurrentPlayerTurn());
                promptPlayerTurn(getCurrentPlayerTurn());
                nextPlayerTurn();
                if (previousPlayer != null && isThereUnoPenalty()) {
                    performUnoPenalty();
                }
                if (getLastDiscardedCard() instanceof IPenalty) {
                    ((IPenalty) getLastDiscardedCard()).performPenalty(this);
                    willPerformAction = false;
                }
                else{
                    willPerformAction = true;
                }
            }
            scoreComputationStrategy.computeScore(players, roundWinner);
            if(maxScore() < winningScore){
                System.out.println("Round winner " + roundWinner.getName() + " has score " + roundWinner.getScore()
                        + " which is less than " + winningScore +
                        ". starting another round...");
                resetGameDeck();
            }
        } while(maxScore() < winningScore);
        announceFinalWinner();
    }
    public void initializePlayers(){
        Scanner sc = new Scanner(System.in);
        System.out.println("How many players do you want to register in the game?");
        int numOfPlayers = sc.nextInt();
        checkNumOfPlayers(numOfPlayers);
        String name;
        while(numOfPlayers-- > 0){
            name = sc.next();
            players.add(new Player(name));
        }
    }
    public final void deal(){
        for(Player player : players){
            for(int i=1;i<=initialHandSize;i++){
                player.addToHand(deck.drawTop());
            }
        }
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void nextPlayerTurn(){
        previousPlayer = currentPlayer;
        if(direction.toString().equals(GameDirection.CLOCKWISE.toString())){
            currentPlayerIndex = (currentPlayerIndex + (players.size() - 1)) % players.size();
        }
        else{
            currentPlayerIndex = ++currentPlayerIndex % players.size();
        }
        currentPlayer = players.get(currentPlayerIndex);
    }
    public void setNextPlayableColor(String nextPlayableColor) {
        if(!deck.doesContainColor(nextPlayableColor)){
            throw new IllegalArgumentException("Color is invalid");
        }
        this.nextPlayableColor = nextPlayableColor;
    }
    public void setNextPlayableFaceValue(String nextPlayableFaceValue) {
        this.nextPlayableFaceValue = nextPlayableFaceValue;
    }
    public void promptUserToChangeColor(){
        Scanner sc = new Scanner(System.in);
        System.out.println("What color do you want to change to?");
        String newColor = sc.next().toUpperCase();
        while(!deck.doesContainColor(newColor)){
            System.out.println("Enter a valid color");
            newColor = sc.next().toUpperCase();
        }
        setNextPlayableColor(newColor);
        setNextPlayableFaceValue(null);
    }
    public boolean isOngoing(){
        for(Player player : players){
            if(player.getHand().size() == 0){
                roundWinner = player;
                return false;
            }
        }
        return true;
    }
    public Player getCurrentPlayerTurn() {
        return currentPlayer;
    }
    public void displayHand(Player player){
        System.out.println(player.getName() + ", your hand has the following cards:");
        for(AbstractCard card : player.getHand()){
            System.out.print(card.getColor() + " " + card.getFaceValue() + ", ");
        }
        System.out.println();
    }
    public void promptPlayerTurn(Player player){
        System.out.println(getCurrentPlayerTurn().getName() + "'s turn...");
        System.out.println("Top card in discard pile is " + getLastDiscardedCard().getColor() + " " +
                getLastDiscardedCard().getFaceValue());
        System.out.println("What card would you like to play?");
        List<AbstractCard> playableCards = player.getPlayableCards(nextPlayableColor, nextPlayableFaceValue);
        int i = 1;
        for(AbstractCard card : playableCards){
            System.out.println(i + "- " + card.getColor() + " " + card.getFaceValue());
            i++;
        }
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        while(choice - 1 >= playableCards.size()){
            System.out.println("Pick a valid card.");
            choice = sc.nextInt();
        }
        player.throwCard(playableCards.get(choice - 1));
        discardPile.add(playableCards.get(choice - 1));

        Random random = new Random();
        double prob = random.nextDouble();
        if(player.getHand().size() == 1 && prob >= 0.5){
            player.setUnoState(true);
            player.shoutUno();
        }
    }
    public boolean isThereUnoPenalty(){
        return (previousPlayer.getHand().size() == 1 && !previousPlayer.isUnoState());
    }

    public void performUnoPenalty(){
        Random random = new Random();
        double prob = random.nextDouble();
        if (prob >= 0.5) {
            System.out.println(getCurrentPlayerTurn().getName() + " found out that " + getPreviousPlayer().getName() +
                    " did not shout UNO!. " + getPreviousPlayer().getName() + " will draw " + missedUnoDrawPenalty + " cards");
            previousPlayer.drawNFromDeck(deck, missedUnoDrawPenalty);
        }
    }
    public AbstractCard getLastDiscardedCard() {
        return discardPile.get(discardPile.size() - 1);
    }
    public int maxScore(){
        int maxScore = players.get(0).getScore();
        for(Player player : players){
            if(player.getScore() > maxScore){
                maxScore = player.getScore();
                maxScorePlayer = player;
            }
        }
        return maxScore;
    }
    public void resetGameDeck(){
        List<AbstractCard> toRemove;
        for(Player player : players){
            toRemove = new ArrayList<>();
            for(AbstractCard card : player.getHand()){
                deck.getDeck().add(card);
                toRemove.add(card);
            }
            for(AbstractCard card: toRemove){
                player.getHand().remove(card);
            }
        }
        toRemove = new ArrayList<>();
        for(AbstractCard card : discardPile){
            deck.add(card);
            toRemove.add(card);
        }
        for(AbstractCard card : toRemove){
            discardPile.remove(card);
        }
        deck.shuffle();
    }
    public void announceFinalWinner(){
        System.out.println("Congrats." + maxScorePlayer.getName() + " won with score " + maxScorePlayer.getScore());
    }
    public Player getPreviousPlayer() {
        return previousPlayer;
    }
    public AbstractDeck getDeck() {
        return deck;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public void toggleDirection(){
        if(direction.toString().equals("CLOCKWISE")){
            direction = GameDirection.ANTICLOCKWISE;
        }
        else{
            direction = GameDirection.CLOCKWISE;
        }
    }
    public String getNextPlayableColor() {
        return nextPlayableColor;
    }
    public String getNextPlayableFaceValue() {
        return nextPlayableFaceValue;
    }
    private void checkNumOfPlayers(int numOfPlayers){
        if(numOfPlayers < minPlayers){
            throw new IllegalArgumentException("The number of players should be equal to or greater than " + minPlayers);
        }
    }
    public void setDealer(Player dealer) {
        this.dealer = dealer;
    }
    public Player getDealer() {
        return dealer;
    }
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public void setWinningScore(int winningScore) {
        this.winningScore = winningScore;
    }

    public void setInitialHandSize(int initialHandSize) {
        this.initialHandSize = initialHandSize;
    }

    public void setDeck(AbstractDeck deck) {
        this.deck = deck;
    }

    public void setDirection(String direction)  {
        if(!direction.equalsIgnoreCase("CLOCKWISE") && !direction.equalsIgnoreCase("ANTICLOCKWISE")){
            throw new IllegalArgumentException("Game direction could either be clockwise or anticlockwise");
        }
        this.direction = GameDirection.valueOf(direction.toUpperCase());
    }
    public void setMissedUnoDrawPenalty(int missedUnoDrawPenalty) {
        this.missedUnoDrawPenalty = missedUnoDrawPenalty;
    }
    public void setNoPlayableCardPenalty(int noPlayableCardPenalty) {
        this.noPlayableCardPenalty = noPlayableCardPenalty;
    }
    public void setDealerDeterminationStrategy(DealerDeterminationStrategy dealerDeterminationStrategy) {
        this.dealerDeterminationStrategy = dealerDeterminationStrategy;
    }
    public void setDeckInitStrategy(DeckInitStrategy deckInitStrategy) {
        this.deckInitStrategy = deckInitStrategy;
    }

    public void setDiscardPileInitStrategy(DiscardPileInitStrategy discardPileInitStrategy) {
        this.discardPileInitStrategy = discardPileInitStrategy;
    }

    public void setDealStrategy(DealerDeterminationStrategy dealerDeterminationStrategy){
        this.dealerDeterminationStrategy = dealerDeterminationStrategy;
    }
    public void setScoreComputationStrategy(ScoreComputationStrategy scoreComputationStrategy){this.scoreComputationStrategy = scoreComputationStrategy;}

}
