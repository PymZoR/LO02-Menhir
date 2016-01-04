package core;


import java.util.ArrayList;

/**
 * The small game
 */
public class Round implements Playable {
    /**
     * The total cards a player can have
     */
    public static final int CARDS_IN_HAND          = 4;
    public static final int ALLIED_CARDS_IN_HAND   = 1;
    public static final int INIT_SMALL_ROCK_NUMBER = 2;

    /**
     * Round state (started, playing player, all players, playing player index and actual season)
     */
    private Playable parent         = null;
    private boolean running         = false;
    private Player currentPlayer    = null;
    private ArrayList<Player> players;
    private int playerNumber        = 0;
    private SeasonType actualSeason = SeasonType.values()[0];
    private int number              = 0;

    /**
     * Create a new game with given player count
     *
     * @param playerNumber The amount of players
     * @param iaPlayers    The IA count
     * @param parent       The parent game
     * @throws java.lang.Exception Throw when player amount is bad
     */
    public Round(int playerNumber, int iaPlayers, Game parent) throws Exception {
        if (playerNumber < 1) {
            throw new Exception("You can't play alone");
        }
        if (playerNumber > 6) {
            throw new Exception("Too many players !");
        }

        this.parent = (parent == null) ? this : parent;

        this.players      = new ArrayList<>();
        this.playerNumber = playerNumber;
        for (int i = 0; i < playerNumber; i++) {
            Player newPlayer;
            if (i >= (playerNumber - iaPlayers)) {
                newPlayer = new IAPlayer(this.parent, i);
            } else {
                newPlayer = new Player(this.parent, i);
            }
            this.players.add(newPlayer);
        }
    }

    /**
     * Create a full game round
     *
     * @param players Existing players
     * @param number  Round index
     */
    public Round(ArrayList<Player> players, int number) {
        this.players      = players;
        this.playerNumber = this.players.size();
        this.number       = number;
    }

    @Override
    public SeasonType getActualSeason() {
        return this.actualSeason;
    }

    @Override
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Get the round number
     *
     * @return The round number
     */
    public int getNumber() {
        return this.number;
    }

    @Override
    public Player getPlayer(int playerId) {
        return this.players.get(playerId);
    }

    @Override
    public int getPlayerNumber() {
        return this.playerNumber;
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public void nextTurn(Card card, ActionType action, Player player) {
        this.currentPlayer.playCard(card, action, player);

        int currentPlayerNumber = this.currentPlayer.getNumber();

        if (currentPlayerNumber == (this.playerNumber - 1)) {
            this.currentPlayer = this.players.get(0);

            if (this.actualSeason.ordinal() == (SeasonType.values().length - 1)) {
                this.running = false;
            } else {
                this.actualSeason = SeasonType.values()[this.actualSeason.ordinal() + 1];
            }

        } else {
            this.currentPlayer = this.players.get(currentPlayerNumber + 1);
        }
    }

    @Override
    public void playFertilizer(Player source, int fertilizeNumber) {
        Field field = source.getField();

        if (field.getSmallRockNumber() < fertilizeNumber) {
            fertilizeNumber = field.getSmallRockNumber();
        }

        field.addSmallRockNumber(-fertilizeNumber);
        field.addBigRockNumber(fertilizeNumber);
    }

    @Override
    public void playGiant(Player source, int smallRockNumber) {
        source.getField().addSmallRockNumber(smallRockNumber);
    }

    @Override
    public void playHobgoblin(Player source, Player target, int hobgoblinNumber) {
        Field targetField = target.getField();
        Field sourceField = source.getField();

        hobgoblinNumber -= target.getProtection();
        target.setProtection(0);

        if (hobgoblinNumber <= 0) {
            return;
        }

        if (targetField.getSmallRockNumber() <= hobgoblinNumber) {
            sourceField.addSmallRockNumber(targetField.getSmallRockNumber());
            targetField.setSmallRockNumber(0);
        } else {
            sourceField.addSmallRockNumber(hobgoblinNumber);
            targetField.addSmallRockNumber(-hobgoblinNumber);
        }

    }

    @Override
    public void playTaupe(Player source, Player target, int bigRockNumber) {
        Field field = target.getField();

        if (field.getBigRockNumber() <= bigRockNumber) {
            field.setBigRockNumber(0);
        } else {
            field.addBigRockNumber(-bigRockNumber);
        }
    }

    /**
     * Set the round number
     *
     * @param number The round number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public void start() {
        Card.resetCards();

        for (int i = 0; i < this.playerNumber; i++) {
            Player player = this.players.get(i);
            player.reset();
        }

        this.currentPlayer = this.players.get(0);
        this.running       = true;
    }
}
