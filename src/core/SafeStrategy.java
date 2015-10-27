package core;


import java.util.ArrayList;

/**
 * Safe strategy : transforms small rocks, or get more
 */
public class SafeStrategy extends Strategy {
    /**
     * Create a new strategy for a given player
     *
     * @param self       The actual player
     * @param allPlayers All the players
     */
    public SafeStrategy(Player self, ArrayList<Player> allPlayers) {
        super(self, allPlayers);
    }

    /**
     * Make the choice
     */
    @Override
    public void makeChoice() {
        this.action   = null;
        this.cardType = null;
        this.target   = null;

        int smallRocks            = this.self.getField().getSmallRockNumber();
        ArrayList<Card> selfCards = this.self.getCards();
        SeasonType actualSeason   = this.self.getGame().getActualSeason();

        if (smallRocks < 2) {
            // Less than two small rocks : play the first giant possible
            for (Card selfCard : selfCards) {
                if (selfCard.getValue(ActionType.GIANT, actualSeason) > 0) {
                    this.cardType = this.self.getCards().get(0).getType();
                }
            }

            this.action = ActionType.GIANT;
        } else {
            // More than two rocks : try to fertilize it using the lowest card
            // possible (to not waste)
            int max = 5; // More than maximum
            Card maxCard = null;
            for (Card c : selfCards) {
                int amount = c.getValue(ActionType.FERTILIZER, actualSeason);

                // Enough to fertilize, but less than the actual chosen card ->
                // better card
                if ((amount >= smallRocks) && (amount < max)) {
                    maxCard = c;
                }
            }

            if (maxCard == null) {
                // Can't find a proper fertilizer, back to giant
                for (Card selfCard : selfCards) {
                    if (selfCard.getValue(ActionType.GIANT, actualSeason) > 0) {
                        this.cardType = this.self.getCards().get(0).getType();
                    }
                }

                this.action = ActionType.GIANT;
            } else {
                this.cardType = maxCard.getType();
                this.action   = ActionType.FERTILIZER;
            }
        }
    }
}
