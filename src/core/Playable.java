package core;


import java.util.ArrayList;

/**
 * Round or full game common API
 */
public interface Playable {
    /**
     * Get the actual season
     *
     * @return The actual season
     */
    public SeasonType getActualSeason();

    /**
     * Get the current player
     *
     * @return The current player
     */
    public Player getCurrentPlayer();

    /**
     * Get a player from its index
     *
     * @param playerId The index
     * @return The player
     */
    public Player getPlayer(int playerId);

    /**
     * Get the number of players
     *
     * @return Get the number of players
     */
    public int getPlayerNumber();

    public ArrayList<Player> getPlayers();

    /**
     * Get the game status
     *
     * @return True if the game is started, false otherwise
     */
    public boolean isRunning();

    /**
     * Make a player play the card and get to the next turn
     *
     * @param card The played card
     * @param action The played action
     * @param player The target player
     */
    public void nextTurn(Card card, ActionType action, Player player);

    /**
     * Make a player play a fertilizer card
     *
     * @param source Source player
     * @param fertilizeNumber Amount of small rocks to evolve
     */
    public void playFertilizer(Player source, int fertilizeNumber);

    /**
     * Make a player play a giant trade card
     *
     * @param source Source player
     * @param smallRockNumber Amount of small rocks to get
     */
    public void playGiant(Player source, int smallRockNumber);

    /**
     * Make a player play a hobgoblin card
     *
     * @param source The source player
     * @param target The target
     * @param hobgoblinNumber Amount of rocks to steal
     */
    public void playHobgoblin(Player source, Player target, int hobgoblinNumber);

    /**
     * Make a player play a taupe card
     *
     * @param source Source player
     * @param target Target player
     * @param bigRockNumber Amount of big rocks to destroy
     */
    public void playTaupe(Player source, Player target, int bigRockNumber);

    /**
     * Start the game
     */
    public void start();
}
