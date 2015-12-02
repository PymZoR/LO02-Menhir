package core;


/**
 * React to a dog event (when the player may invoke dog ard)
 */
public interface DogListener {
    void wouldPlayerPlayDog(Player player, int stolenSeeds);
}
