package core;

import java.util.Vector;

public class HarassStrategy extends Strategy {
	public HarassStrategy(Player self, Vector<Player> allPlayers) {
		super(self, allPlayers);
	}

	@Override
	public void makeChoice() {
		this.action = null;
		this.card   = null;
		this.target = null;

		Game game = this.self.getGame();

		// Detect best player
		for (int i = 0; i < this.allPlayers.size(); i++) {
			Player p = this.allPlayers.get(i);

			if (this.target == null) {
				this.target = p;
				continue;
			}

			if (this.target.getField().getBigRockNumber() < p.getField().getBigRockNumber()) {
				this.target = p;
				continue;
			}

			if (this.target.getField().getBigRockNumber() == p.getField().getBigRockNumber() &&
				this.target.getField().getSmallRockNumber() < p.getField().getSmallRockNumber()) {
				this.target = p;
				continue;
			}
		}

		// Try farfadet
		Vector<Card> selfCards = this.self.getCards();
		for (int i = 0; i < selfCards.size(); i++) {
			int hobgoblinPower = selfCards.get(i).getValue(ActionType.HOBGOBLIN, game.getActualSeason());

			// Try farfadet against best player
			if (hobgoblinPower <= this.target.getField().getSmallRockNumber()) {
				this.card   = selfCards.get(i).getType();
				this.action = ActionType.HOBGOBLIN;
				break;
			}

			// Try farfadet against other
			for (int j = 0; j < this.allPlayers.size(); j++) {
				if (hobgoblinPower <= this.allPlayers.get(j).getField().getSmallRockNumber()) {
					this.card   = selfCards.get(i).getType();
					this.action = ActionType.HOBGOBLIN;
					break;
				}
			}
		}

		// If can't play farfadet, back to Safe
		if (this.action != ActionType.HOBGOBLIN) {
			SafeStrategy ss = new SafeStrategy(this.self, this.allPlayers);
			ss.makeChoice();
			this.card   = ss.getCard();
			this.action = ss.getAction();
		}

		// If has taupe and best player has > 1 big rock; play taupe
		if (this.target.getField().getBigRockNumber() > 1) {
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
