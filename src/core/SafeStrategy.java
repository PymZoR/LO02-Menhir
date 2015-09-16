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
		
		// If can convert >2 rocks => convert maximum possible
		// Else get from giant maximum possible
	}
}
