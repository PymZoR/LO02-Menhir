package core;


import java.util.ArrayList;

/**
 * Aggressive strategy
 */
public class HarassStrategy implements Strategy {
    @Override
    public StrategyResult makeChoice(Player self, ArrayList<Player> allPlayers) {
        ActionType action       = null;
        CardType cardType       = null;
        CardType alliedCardType = null;
        Player target           = null;

        Playable game = self.game;

        // Detect best player
        for (Player p : allPlayers) {
            // Do not target yourself
            if (p == self) {
                continue;
            }

            if (target == null) {
                target = p;
                continue;
            }

            if (target.getField().getBigRockNumber() < p.getField().getBigRockNumber()) {
                target = p;
                continue;
            }

            if ((target.getField().getBigRockNumber() == p.getField().getBigRockNumber())
               && (target.getField().getSmallRockNumber() < p.getField().getSmallRockNumber())) {
                target = p;
            }
        }

        // Try hobgoblin
        ArrayList<Card> selfCards = self.getCards();
        for (Card selfCard : selfCards) {
            int hobgoblinPower = selfCard.getValue(ActionType.HOBGOBLIN, game.getActualSeason());
            // Try hobgoblin against best player
            if (hobgoblinPower <= target.getField().getSmallRockNumber()) {
                cardType = selfCard.getType();
                action   = ActionType.HOBGOBLIN;
                break;
            }
            // Try hobgoblin against other
            for (Player p : allPlayers) {
                // Do not target yourself
                if (p == self) {
                    continue;
                }
                if (hobgoblinPower <= p.getField().getSmallRockNumber()) {
                    cardType = selfCard.getType();
                    action   = ActionType.HOBGOBLIN;
                    break;
                }
            }
        }

        // If can't play hobgoblin, back to Safe
        if (action != ActionType.HOBGOBLIN) {
            SafeStrategy ss         = new SafeStrategy();
            StrategyResult ssResult = ss.makeChoice(self, allPlayers);

            target   = null;
            cardType = ssResult.cardType;
            action   = ssResult.type;
        }

        // If playing hobgobblin with strength = 0, back to Safe
        if ((action == ActionType.HOBGOBLIN)
            && (Card.getCard(cardType).getValue(action, game.getActualSeason()) == 0)) {
            SafeStrategy ss         = new SafeStrategy();
            StrategyResult ssResult = ss.makeChoice(self, allPlayers);

            target   = null;
            cardType = ssResult.cardType;
            action   = ssResult.type;
        }

        // If has taupe and best player has > 1 big rock; play taupe
        if ((target != null) && (target.getField().getBigRockNumber() > 1)) {
            ArrayList<AlliedCard> alliedCards = self.getAlliedCards();
            for (int i = 0; i < alliedCards.size(); i++) {
                switch (alliedCards.get(i).getType()) {
                    case TAUPE1:
                    case TAUPE2:
                    case TAUPE3:
                        alliedCardType = alliedCards.get(i).getType();
                        break;
                    default:
                        break;
                }
            }
        }

        return new StrategyResult(cardType, action, target, alliedCardType);
    }
}
