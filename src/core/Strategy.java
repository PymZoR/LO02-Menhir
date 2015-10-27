package core;


import java.util.ArrayList;

/**
 * Base Strategy interface
 */
interface Strategy {
    public StrategyResult makeChoice(Player self, ArrayList<Player> allPlayers);
}
