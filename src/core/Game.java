package core;

import java.util.Vector;


public class Game {
    public static final int CARDS_IN_HAND = 4;
	private boolean running = false;
	private Player currentPlayer = null;
	private Vector<Player> players;
	private int playerNumber = 0;

    public Game(int playerNumber) {
    	this.players = new Vector<Player>();
    	this.playerNumber = playerNumber;

    	for (int i = 0; i < playerNumber; i++) {
    		Player newPlayer = new Player();
    		newPlayer.drawCard(4);
    		players.add(newPlayer);
    	}

    	this.currentPlayer = this.players.get(0);
    }

    public void start() {
    	this.running = true;
    }
    
    public void nextTurn(Card card, ActionType action, Player player) {
        this.currentPlayer.playCard(card, action, player);
    }

    public Player getPlayer(int playerId) {
    	return players.get(playerId);
    }

    public boolean isRunning() {
        return this.running;
    }


    public Player getCurrentPlayer() {
    	return this.currentPlayer;
    }


    public int getPlayerNumber() {
    	return this.playerNumber;
    }
}
