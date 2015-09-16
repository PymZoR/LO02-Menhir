package core;

import java.util.Vector;

abstract class Strategy {
	Player self;
	Vector<Player> allPlayers;
	
	Player target;
	ActionType action;
	CardType card;
	CardType alliedCard;
	
	public Strategy(Player self, Vector<Player> allPlayers) {
		this.self = self;
		this.allPlayers = allPlayers;
		
		this.allPlayers.removeElement(self);
		
		this.makeChoice();
	}

	abstract public void makeChoice();
	
	public Player getTarget() {
		return this.target;
	}

	public ActionType getAction() {
		return this.action;
	}

	public CardType getCard() {
		return this.card;
	}
	
	public CardType getAlliedCard() {
		return this.alliedCard;
	}
}
