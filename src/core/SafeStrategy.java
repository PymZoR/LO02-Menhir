package core;

import java.util.Vector;

public class SafeStrategy extends Strategy {
	public SafeStrategy(Player self, Vector<Player> allPlayers) {
		super(self, allPlayers);
	}

	@Override
	public void makeChoice() {
		this.action = null;
		this.card   = null;
		this.target = null;
		
		int smallRocks = this.self.getField().getSmallRockNumber();
		
		if (smallRocks < 2) {
			this.card   = this.self.getCards().firstElement().getType();
			this.action = ActionType.GIANT;
		} else {
			Vector<Card> selfCards = this.self.getCards();
			
			int max = 0;
			Card maxCard = null;
			for (int i = 0; i < selfCards.size(); i++) {
				Card c     = selfCards.get(i);
				int amount = c.getValue(ActionType.FERTILIZER, this.self.getGame().getActualSeason());
				
				if (amount > max) {
					maxCard = c;
				}
			}
			
			this.card   = maxCard.getType();
			this.action = ActionType.FERTILIZER; 
		}
	}
}
