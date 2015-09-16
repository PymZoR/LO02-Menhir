package core;

import java.util.Random;
import java.util.Vector;


public class Player {
	private Vector<Card> cards;
	private Field field;
	private int number;
	private Game game;

	public Player(Game game, int number) {
		this.cards  = new Vector<Card>();
		this.field  = new Field();
		this.number = number;
		this.game = game;
	}

	public void drawCard(int cardNumber) {
		for (int i = 0; i < cardNumber; i++) {
			CardType randomType = CardType.values()[new Random().nextInt(Card.CARD_NUMBER)];
			Card newCard = Card.getCard(randomType);
			this.cards.add(newCard);
		}
	}

	public void playCard(Card card, ActionType action, Player target) {
		int actionValue = card.getValue(action, this.game.getActualSeason());

		switch (action) {
			case GIANT:
				this.game.playGiant(this, actionValue);
				break;

			case FERTILIZER:
				this.game.playFertilizer(this, actionValue);
				break;

			case HOBGOBLIN:
				this.game.playHobgoblin(target, actionValue);
				break;
		}
	}
	
	public Vector<Card> getCards () {
		return this.cards;
	}

	public Card getCardById(int cardId) {
		return this.cards.get(cardId);
	}

	public Field getField() {
		return this.field;
	}

	public int getNumber() {
		return this.number;
	}

	public Game getGame() {
		return this.game;
	}
}
