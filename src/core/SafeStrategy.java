package core;


import java.util.ArrayList;

/**
 * Safe strategy : transforms small rocks, or get more
 */
public class SafeStrategy implements Strategy {
    /**
     * Make the choice
     * @param  self       The actual player
     * @param  allPlayers All the players
     * @return The choosen triplet {cardType,target,alliedCardType}
     */
    @Override
    public StrategyResult makeChoice(Player self, ArrayList<Player> allPlayers) {
        ActionType action   = null;
        CardType cardType = null;
        Player target   = null;

        int smallRocks            = self.getField().getSmallRockNumber();
        ArrayList<Card> selfCards = self.getCards();
        SeasonType actualSeason   = self.getGame().getActualSeason();

        if (smallRocks < 2) {
            // Less than two small rocks : play the first giant possible
            for (Card selfCard : selfCards) {
                if (selfCard.getValue(ActionType.GIANT, actualSeason) > 0) {
                    cardType = self.getCards().get(0).getType();
                }
            }

            action = ActionType.GIANT;
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
                        cardType = self.getCards().get(0).getType();
                    }
                }

                action = ActionType.GIANT;
            } else {
                cardType = maxCard.getType();
                action   = ActionType.FERTILIZER;
            }
        }

        return new StrategyResult(cardType, action, target, null);
    }
}
