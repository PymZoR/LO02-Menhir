package core;


import java.util.Vector;

/**
 * Safe strategy : transforms small rocks, or get more
 */
public class SafeStrategy extends Strategy {
    /**
     * Create a new strategy for a given player
     * 
     * @param self
     *            The actual player
     * @param allPlayers
     *            All the players
     */
    public SafeStrategy(Player self, Vector<Player> allPlayers) {
        super(self, allPlayers);
    }

    /**
     * Make the choice
     */
    @Override
    public void makeChoice() {
        this.action = null;
        this.card = null;
        this.target = null;

        int smallRocks = this.self.getField().getSmallRockNumber();
        Vector<Card> selfCards = this.self.getCards();
        SeasonType actualSeason = this.self.getGame().getActualSeason();

        if (smallRocks < 2) {
            // Less than two small rocks : play the first giant possible
            for (int i = 0; i < selfCards.size(); i++) {
                if (selfCards.get(i).getValue(ActionType.GIANT, actualSeason) > 0) {
                    this.card = this.self.getCards().firstElement().getType();
                }
            }

            this.action = ActionType.GIANT;
        } else {
            // More than two rocks : try to fertilize it using the lowest card
            // possible (to not waste)
            int max = 5; // More than maximum
            Card maxCard = null;
            for (int i = 0; i < selfCards.size(); i++) {
                Card c = selfCards.get(i);
                int amount = c.getValue(ActionType.FERTILIZER, actualSeason);

                // Enough to fertilize, but less than the actual chosen card ->
                // better card
                if ((amount >= smallRocks) && (amount < max)) {
                    maxCard = c;
                }
            }

            if (maxCard == null) {
                // Can't find a proper fertilizer, back to giant
                for (int i = 0; i < selfCards.size(); i++) {
                    if (selfCards.get(i).getValue(ActionType.GIANT, actualSeason) > 0) {
                        this.card = this.self.getCards().firstElement().getType();
                    }
                }

                this.action = ActionType.GIANT;
            } else {
                this.card = maxCard.getType();
                this.action = ActionType.FERTILIZER;
            }
        }
    }
}
