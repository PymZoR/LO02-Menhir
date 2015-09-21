package core;


import java.util.Vector;

/**
 * The game
 */
public class Game {
	/**
	 * The total cards a player can have
	 */
	public static final int CARDS_IN_HAND = 4;

	/**
	 * Game state (started, playing player, all players, playing player index and actual season)
	 */
	private boolean running = false;
	private Player currentPlayer = null;
	private Vector<Player> players;
	private int playerNumber = 0;
	private SeasonType actualSeason = SeasonType.values()[0];

	/**
	 * Create a new game with given player count
	 * @param playerNumber The amount of players
	 */
	public Game(int playerNumber) {
		if (playerNumber <= 1) {
			throw new Error("You can't play alone");
		}

		this.players = new Vector<Player>();
		this.playerNumber = playerNumber;

		// Init players
		for (int i = 0; i < playerNumber; i++) {
			Player newPlayer = new Player(this, i);
			newPlayer.drawCard(Game.CARDS_IN_HAND);
			newPlayer.getField().addSmallRockNumber(2);

			players.add(newPlayer);
		}

		this.currentPlayer = this.players.get(0);
	}

	/**
	 * Start the game
	 */
	public void start() {
		this.running = true;
	}

	/**
	 * Make a player play the card and get to the next turn
	 * @param card   The played card
	 * @param action The played action
	 * @param player The player
	 */
	public void nextTurn(Card card, ActionType action, Player player) {
		this.currentPlayer.playCard(card, action, player);

		int currentPlayerNumber = this.currentPlayer.getNumber();
		if (currentPlayerNumber == this.playerNumber - 1) {
			this.currentPlayer = this.players.get(0);

			if (this.actualSeason.ordinal() == SeasonType.values().length - 1) {
				this.running = false;
			}
			else {
				this.actualSeason = SeasonType.values()[this.actualSeason.ordinal() + 1];
			}

		}
		else {
			this.currentPlayer = this.players.get(currentPlayerNumber + 1);
		}
	}

	/**
	 * Make a player play a taupe card
	 * @param source        Source player
	 * @param target        Target player
	 * @param bigRockNumber Amount of big rocks to destroy
	 */
	public void playTaupe(Player source, Player target, int bigRockNumber) {
		Field field = target.getField();
		field.addBigRockNumber(-bigRockNumber);
	}

	/**
	 * Make a player play a dog card
	 * @param source Source player
	 */
	public void playDog(Player source) {
		// TODO
	}

	/**
	 * Make a player play a giant trade card
	 * @param source          Source player
	 * @param smallRockNumber Amount of small rocks to get
	 */
	public void playGiant(Player source, int smallRockNumber) {
		source.getField().addSmallRockNumber(smallRockNumber);
	}

	/**
	 * Make a player play a fertilizer card
	 * @param source          Source player
	 * @param fertilizeNumber Amount of small rocks to evolve
	 */
	public void playFertilizer(Player source, int fertilizeNumber) {
		Field field = source.getField();

		if (field.getSmallRockNumber() < fertilizeNumber) {
			fertilizeNumber = field.getSmallRockNumber();
		}

		field.addSmallRockNumber(-fertilizeNumber);
		field.addBigRockNumber(fertilizeNumber);
	}

	/**
	 * Make a player play a hobgoblin card
	 * @param target          The target
	 * @param hobgoblinNumber Amount of rocks to steal
	 */
	public void playHobgoblin(Player target, int hobgoblinNumber) {
		Field field = target.getField();

		if (field.getSmallRockNumber() <= hobgoblinNumber) {
			field.setSmallRockNumber(0);
		}
		else {
			field.addSmallRockNumber(-hobgoblinNumber);
		}
	}

	/**
	 * Get a player from its index
	 * @param playerId The index
	 * @return The player
	 */
	public Player getPlayer(int playerId) {
		return players.get(playerId);
	}

	/**
	 * Get the game status
	 * @return True if the game is started, false otherwise
	 */
	public boolean isRunning() {
		return this.running;
	}

	/**
	 * Get the current player
	 * @return The current player
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * Get the number of players
	 * @return Get the number of players
	 */
	public int getPlayerNumber() {
		return this.playerNumber;
	}

	/**
	 * Get the actual season
	 * @return The actual season
	 */
	public SeasonType getActualSeason() {
		return this.actualSeason;
	}
}
