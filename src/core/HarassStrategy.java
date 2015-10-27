package core;


import java.util.ArrayList;

/**
 * Aggressive strategy
 */
public class HarassStrategy extends Strategy {
    /**
     * Create a new strategy for a given player
     *
     * @param self       The actual player
     * @param allPlayers All the players
     */
    public HarassStrategy(Player self, ArrayList<Player> allPlayers) {
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

        Playable game = this.self.game;

        // Detect best player
        for (Player p : this.allPlayers) {
            // Do not target yourself
            if (p == this.self) {
                continue;
            }

            if (this.target == null) {
                this.target = p;
                continue;
            }

            if (this.target.getField().getBigRockNumber() < p.getField().getBigRockNumber()) {
                this.target = p;
                continue;
            }

            if ((this.target.getField().getBigRockNumber() == p.getField().getBigRockNumber())
                && (this.target.getField().getSmallRockNumber() < p.getField().getSmallRockNumber())) {
                this.target = p;
            }
        }

        // Try hobgoblin
        ArrayList<Card> selfCards = this.self.getCards();
        for (Card selfCard : selfCards) {
            int hobgoblinPower = selfCard.getValue(ActionType.HOBGOBLIN, game.getActualSeason());
            // Try hobgoblin against best player
            if (hobgoblinPower <= this.target.getField().getSmallRockNumber()) {
                this.cardType = selfCard.getType();
                this.action   = ActionType.HOBGOBLIN;
                break;
            }
            // Try hobgoblin against other
            for (Player p : this.allPlayers) {
                // Do not target yourself
                if (p == this.self) {
                    continue;
                }
                if (hobgoblinPower <= p.getField().getSmallRockNumber()) {
                    this.cardType = selfCard.getType();
                    this.action   = ActionType.HOBGOBLIN;
                    break;
                }
            }
        }

        // If can't play hobgoblin, back to Safe
        if (this.action != ActionType.HOBGOBLIN) {
            SafeStrategy ss = new SafeStrategy(this.self, this.allPlayers);
            this.target = null;
            ss.makeChoice();
            this.cardType = ss.getCardType();
            this.action   = ss.getAction();
        }

        // If playing hobgobblin with strength = 0, back to Safe
        if ((this.action == ActionType.HOBGOBLIN)
            && (Card.getCard(this.cardType).getValue(this.action, game.getActualSeason()) == 0)) {
            SafeStrategy ss = new SafeStrategy(this.self, this.allPlayers);
            this.target = null;
            ss.makeChoice();
            this.cardType = ss.getCardType();
            this.action   = ss.getAction();
        }

        // If has taupe and best player has > 1 big rock; play taupe
        if ((this.target != null) && (this.target.getField().getBigRockNumber() > 1)) {
            ArrayList<AlliedCard> alliedCards = this.self.getAlliedCards();
            for (int i = 0; i < alliedCards.size(); i++) {
                switch (alliedCards.get(i).getType()) {
                    case TAUPE1:
                    case TAUPE2:
                    case TAUPE3:
                        this.alliedCardType = alliedCards.get(i).getType();
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
