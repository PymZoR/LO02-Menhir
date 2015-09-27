package core;


import java.util.Vector;

/**
 * Aggressive strategy
 */
public class HarassStrategy extends Strategy {
	/**
	 * Create a new strategy for a given player
	 * @param self       The actual player
	 * @param allPlayers All the players
	 */
	public HarassStrategy(Player self, Vector<Player> allPlayers) {
		super(self, allPlayers);
	}

	/**
	 * Make the choice
	 */
	@Override
	public void makeChoice() {
		this.action = null;
		this.card   = null;
		this.target = null;

		Playable game = this.self.game;

		// Detect best player
		for (int i = 0; i < this.allPlayers.size(); i++) {
			Player p = this.allPlayers.get(i);

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

			if ((this.target.getField().getBigRockNumber() == p.getField().getBigRockNumber()) &&
					(this.target.getField().getSmallRockNumber() < p.getField().getSmallRockNumber())) {
				this.target = p;
				continue;
			}
		}

		// Try hobgoblin
		Vector<Card> selfCards = this.self.getCards();
		for (int i = 0; i < selfCards.size(); i++) {
			int hobgoblinPower = selfCards.get(i).getValue(ActionType.HOBGOBLIN, game.getActualSeason());

			// Try hobgoblin against best player
			if (hobgoblinPower <= this.target.getField().getSmallRockNumber()) {
				this.card   = selfCards.get(i).getType();
				this.action = ActionType.HOBGOBLIN;
				break;
			}

			// Try hobgoblin against other
			for (int j = 0; j < this.allPlayers.size(); j++) {
				// Do not target yourself
				if (this.allPlayers.get(j) == this.self) {
					continue;
				}

				if (hobgoblinPower <= this.allPlayers.get(j).getField().getSmallRockNumber()) {
					this.card   = selfCards.get(i).getType();
					this.action = ActionType.HOBGOBLIN;
					break;
				}
			}
		}

		// If can't play hobgoblin, back to Safe
		if (this.action != ActionType.HOBGOBLIN) {
			SafeStrategy ss = new SafeStrategy(this.self, this.allPlayers);
			this.target = null;
			ss.makeChoice();
			this.card   = ss.getCard();
			this.action = ss.getAction();
		}

		// If playing hobgobblin with strength = 0, back to Safe
		if ((this.action == ActionType.HOBGOBLIN) && (Card.getCard(this.card).getValue(this.action, game.getActualSeason()) == 0)) {
			SafeStrategy ss = new SafeStrategy(this.self, this.allPlayers);
			this.target = null;
			ss.makeChoice();
			this.card   = ss.getCard();
			this.action = ss.getAction();
		}

		// If has taupe and best player has > 1 big rock; play taupe
		if ((this.target != null) && (this.target.getField().getBigRockNumber() > 1)) {
			Vector<AlliedCard> alliedCards = this.self.getAlliedCards();
			for (int i = 0; i < alliedCards.size(); i++) {
				switch(alliedCards.get(i).getType()) {
				case TAUPE1:
				case TAUPE2:
				case TAUPE3:
					this.alliedCard = alliedCards.get(i).getType();
					break;
				default:
					break;
				}
			}
		}
	}
}
