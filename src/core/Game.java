package core;

import java.util.Vector;


public class Game {
    public static final int CARDS_IN_HAND = 4;
	private boolean running = false;
	private Player currentPlayer = null;
	private Vector<Player> players;
	private int playerNumber = 0;
	private SeasonType actualSeason = SeasonType.values()[0];

    public Game(int playerNumber) {
    	this.players = new Vector<Player>();
    	this.playerNumber = playerNumber;

        // Init players
    	for (int i = 0; i < playerNumber; i++) {
            Player newPlayer = new Player(this, i);
            newPlayer.drawCard(4);
            newPlayer.getField().addSmallRockNumber(2);

            players.add(newPlayer);
    	}

    	this.currentPlayer = this.players.get(0);
    }

    public void start() {
    	this.running = true;
    }

    public void nextTurn(Card card, ActionType action, Player player) {
        this.currentPlayer.playCard(card, action, player);

        int currentPlayerNumber = this.currentPlayer.getNumber();
        if (currentPlayerNumber == this.playerNumber - 1) {
            this.currentPlayer = this.players.get(0);

            if (this.actualSeason.ordinal() == SeasonType.values().length - 1) {
            	this.actualSeason = SeasonType.values()[0];
            }
            else {
            	this.actualSeason = SeasonType.values()[this.actualSeason.ordinal() + 1];
            }

        }
        else {
            this.currentPlayer = this.players.get(currentPlayerNumber + 1);
        }
    }

    public void playTaupe(Player source, Player target, int bigRockNumber) {
        Field field = target.getField();
        field.addBigRockNumber(-bigRockNumber);
	}

	public void playDog(Player source) {
        // TODO
	}

	public void playGiant(Player source, int smallRockNumber) {
        source.getField().addSmallRockNumber(smallRockNumber);
	}

	public void playFertilizer(Player source, int fertilizeNumber) {
        Field field = source.getField();

        if (field.getSmallRockNumber() <= fertilizeNumber) {
            throw new Error("Not enough small rocks");
        }

        field.addSmallRockNumber(-fertilizeNumber);
        field.addBigRockNumber(fertilizeNumber);
	}

	public void playHobgoblin(Player target, int hobgoblinNumber) {
        Field field = target.getField();

        if (field.getSmallRockNumber() <= hobgoblinNumber) {
            field.setSmallRockNumber(0);
        }
        else {
            field.addSmallRockNumber(-hobgoblinNumber);
        }
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

    public SeasonType getActualSeason() {
    	return this.actualSeason;
    }
}
