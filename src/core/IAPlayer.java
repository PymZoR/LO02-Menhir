package core;


import java.util.Random;

/**
 * Computer-powered player
 */
public class IAPlayer extends Player {
    /**
     * IA
     */
    private Strategy strategy = null;

    public IAPlayer(Playable game, int number) {
        super(game, number);

        this.isIA = true;

        int strategyNumber = (new Random()).nextInt(Strategy.STRATEGY_COUNT + 1);

        System.out.print("Player " + String.valueOf(this.number + 1) + " is ");

        if (strategyNumber == 0) {
            System.out.println("safe");
            this.strategy = new SafeStrategy(this, this.game.getPlayers());
        } else if (strategyNumber == 1) {
            System.out.println("aggressive");
            this.strategy = new HarassStrategy(this, this.game.getPlayers());
        }
    }

    /**
     * Get the chosen action
     * 
     * @return The action
     */
    public ActionType getAction() {
        return this.strategy.action;
    }

    /**
     * Get the optional played allied card
     * 
     * @return The allied card
     */
    public CardType getAlliedCard() {
        return this.strategy.alliedCard;
    }

    /**
     * Get the chosen card
     * 
     * @return The card id
     */
    public CardType getCard() {
        return this.strategy.card;
    }

    /**
     * Get the optional target
     * 
     * @return The target
     */
    public Player getTarget() {
        return this.strategy.target;
    }

    /**
     * Make the strategy choice
     */
    public void makeChoice() {
        this.strategy.makeChoice();
    }
}
