package core;


import java.util.ArrayList;

/**
 * Base Strategy class
 */
abstract class Strategy {
    static public final int STRATEGY_COUNT = 1;

    /**
     * The actual player
     */
    Player self;

    /**
     * All the players
     */
    ArrayList<Player> allPlayers;

    /**
     * Chosen player to attack
     */
    Player target;

    /**
     * Strategy choices
     */
    ActionType action;
    CardType cardType;
    CardType alliedCardType;

    /**
     * Create a new strategy for a given player
     *
     * @param self The actual player
     * @param allPlayers All the players
     */
    public Strategy(Player self, ArrayList<Player> allPlayers) {
        this.self       = self;
        this.allPlayers = allPlayers;
    }

    /**
     * Get the chosen action
     *
     * @return The action
     */
    public ActionType getAction() {
        return this.action;
    }

    /**
     * Get the optional played allied card type
     *
     * @return The allied card type
     */
    public CardType getAlliedCardType() {
        return this.alliedCardType;
    }

    /**
     * Get the chosen card type
     *
     * @return The card type
     */
    public CardType getCardType() {
        return this.cardType;
    }

    /**
     * Get the optional target
     *
     * @return The target
     */
    public Player getTarget() {
        return this.target;
    }

    /**
     * Sets the three variables to make the choice given the environment
     */
    abstract public void makeChoice();
}
