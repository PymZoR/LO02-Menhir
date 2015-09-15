package core;


public class Player {
	public void playCard(Card card, ActionType action, Player target) {
		switch (card.getType()) {
			case TAUPE1:
			case TAUPE2:
			case TAUPE3:
				this.playTaupe(target);
				break;
			case DOG1:
			case DOG2:
			case DOG3:
				this.playDog();
				break;
			default:
				switch (action) {
					case GIANT:
						this.playGiant(card);
						break;
					case FERTILIZER:
						this.playFertilizer(card);
						break;
					case HOBGOBLIN:
						this.playHobgobblin(card, target);
						break;
				}
		}
	}
	
	public void playCard(Card card, ActionType action) {
		playCard(card, action, null);
	}
	
	private void playTaupe(Player target) {
		
	}
	
	private void playDog() {
		
	}
	
	private void playGiant (Card card) {
	}
	
	private void playFertilizer (Card card) {
		
	}
	
	private void playHobgobblin (Card card, Player target) {
		
	}
}
