package core;


import java.util.Random;

/**
 * Computer-powered player
 */
public class IAPlayer extends Player {
    static public final int STRATEGY_COUNT = 1;

    /**
     * IA
     */
    private Strategy strategy = null;

    public IAPlayer(Playable game, int number) {
        super(game, number);

        this.isIA = true;

        int strategyNumber = (new Random()).nextInt(STRATEGY_COUNT + 1);

        System.out.print("IA Player " + String.valueOf(this.number + 1) + " is ");

        if (strategyNumber == 0) {
            System.out.println("safe.");
            this.strategy = new SafeStrategy();
        } else if (strategyNumber == 1) {
            System.out.println("aggressive.");
            this.strategy = new HarassStrategy();
        }
    }

    /**
     * Make the strategy choice
     */
    public StrategyResult makeChoice() {
        return this.strategy.makeChoice(this, this.game.getPlayers());
    }
}
