package core;


/**
 * React to a dog event (when the player may invoke dog ard)
 */
public interface DogListener {
    /**
     * Triggered when a hobgoblin is launched at player
     * 
     * @param player The target player
     * @param stolenSeeds The amount of stolen seeds
     */
    void wouldPlayerPlayDog(Player player, int stolenSeeds);
}
